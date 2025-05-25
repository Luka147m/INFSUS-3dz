import React, { useState, useEffect } from 'react';
import { toast } from 'react-toastify';

import '../styles/EvidencijskaListaTablica.css'
import {
  getEvidencijaByDijeteId,
  updateEvidencija,
  deleteEvidencija,
  createEvidencija
} from '../api';

import { getOdgojitelji } from '../api';

const EvidencijskaListaTablica = ({ evidencija, dijete, setEvidencija, setEditingEvidencija}) => {
  const [editId, setEditId] = useState(null);
  const [editFormData, setEditFormData] = useState({});
  const [isAdding, setIsAdding] = useState(false);
  const [newFormData, setNewFormData] = useState({
    idOdgojitelj: '',
    datum: '',
    prisutan: 't',
    program: '',
    napomena: ''
  });
  const [validationError, setValidationError] = useState('');
  const [odgojitelji, setOdgojitelji] = useState([]);

  useEffect(() => {
    getOdgojitelji()
      .then(data => setOdgojitelji(data))
      .catch(err => console.error('Greška pri učitavanju odgojitelja:', err));
  }, []);

  const validateForm = (formData) => {
      if (!formData.idOdgojitelj) {
        return 'Odgojitelj je obavezan.'; 
      }
      if (!formData.datum || formData.datum.trim() === '') {
        return 'Datum je obavezan.';
      }
      const danas = new Date();
      danas.setHours(23,59,59,59); 
      const uneseniDatum = new Date(formData.datum);

      if (uneseniDatum > danas) {
        return 'Datum ne smije biti u budućnosti.';
      }
      if (!formData.program || formData.program.trim() === '') {
        return 'Program je obavezan.';
      }

      if (formData.napomena.trim().length > 150) {
        return 'Napomena ne smije biti duža od 150 znakova.';
      }
      return '';
};


  const startEditing = (item) => {
    setEditId(item.idEvidencijskaLista);
    setEditFormData({
      ...item,
      prisutan: item.prisutan ? 't' : 'f',
    });
  };

  const cancelEditing = () => {
    setValidationError('')
    setEditId(null);
    setEditFormData({});
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditFormData(prev => ({ ...prev, [name]: value }));
  };

  const saveEdit = () => {
     const error = validateForm(editFormData);
      if (error) {
        setValidationError(error);
        return;
      }
      setValidationError('');
    const updated = {
      ...editFormData,
      prisutan: editFormData.prisutan === 't',
    };
    console.log(updated)
    handleSave(updated);
    setEditId(null);
    setEditFormData({});
  };

  const handleNewChange = (e) => {
    const { name, value } = e.target;
    setNewFormData(prev => ({ ...prev, [name]: value }));
  };

  const saveNew = () => {
    const error = validateForm(newFormData);
    if (error) {
      setValidationError(error);
      return;
    }
    setValidationError('');

    const newRecord = {
      ...newFormData,
      prisutan: newFormData.prisutan === 't',
    };
    handleAddEvidencija(newRecord);
    setIsAdding(false);
    setNewFormData({
      idOdgojitelj: '',
      datum: '',
      prisutan: 't',
      program: '',
      napomena: ''
    });
  };

  const cancelNew = () => {
    setIsAdding(false);
    setValidationError('')
    setNewFormData({
      idOdgojitelj: '',
      datum: '',
      prisutan: 't',
      program: '',
      napomena: ''
    });
  };

  const handleSave = async (updated) => {
    try {
      await updateEvidencija(updated);
      toast.success('Evidencija uspješno ažurirana!');
  
      setEvidencija(prev => prev.map(el =>
        el.idEvidencijskaLista === updated.idEvidencijskaLista ? updated : el
      ));
      setEditingEvidencija(null);
    } catch (err) {
      console.error('Greška pri spremanju evidencije:', err);
      toast.error('Došlo je do pogreške prilikom spremanja promjena!');
  
    }
  };

  // const handleEditEvidencija = (item) => {
  //   setEditingEvidencija(item);
  // };
  
  const handleDeleteEvidencija = (id) => {
    deleteEvidencija(id)
      .then(() => setEvidencija(prev => prev.filter(el => el.idEvidencijskaLista !== id)))
      .catch(err => console.error('Greška pri brisanju:', err));
  }

  const handleAddEvidencija = async (newRecord) => {
    // console.log(newRecord)
    try {
      // const maxId = evidencija.reduce((max, el) => (el.idEvidencijskaLista > max ? el.idEvidencijskaLista : max), 0);
      // const recordWithId = { ...newRecord, idEvidencijskaLista: maxId + 1,     };
      // const recordWithIdChild = {...recordWithId, idDijete: dijete.idDijete,}
      const recordWithId = { ...newRecord, idDijete: dijete.idDijete };
      await createEvidencija(recordWithId);
      toast.success('Evidencija uspješno kreirana!')
      try {
        const evidencijaData = await getEvidencijaByDijeteId(dijete.idDijete);
        setEvidencija(evidencijaData);
      } catch (err) {
        console.error('Greška prilikom dohvaćanja evidencije:', err);
        setEvidencija([]);
      }
      // setEvidencija(prev => [...prev, recordWithId]);
    } catch (err) {
      console.error('Greška pri dodavanju novog zapisa:', err);
      toast.error('Došlo je do pogreške!')
    }
  };
  
  return (
    <div className="evidencijska-container">
      <button
        style={{ marginBottom: '10px', padding: '6px 12px', borderRadius: '5px', cursor: 'pointer' }}
        onClick={() => setIsAdding(true)}
        disabled={isAdding || editId !== null}
      >
        ➕ Dodaj novi zapis
      </button>
      {validationError && (
      <div className="validation-error" style={{ color: 'red', margin: '10px 0' }}>
        {validationError}
      </div>
    )}

      <table className="evidencijska-tablica">
        <thead>
          <tr>
            <th>ID</th>
            <th>Odgojitelj</th>
            <th>Datum</th>
            <th>Prisutan</th>
            <th>Program</th>
            <th>Napomena</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {isAdding && (
            <tr>
              <td>—</td>
              <td>
                <select
                  name="idOdgojitelj"
                  value={newFormData.idOdgojitelj}
                  onChange={handleNewChange}
                >
                <option value="">-- Odaberite odgojitelja --</option>
                {odgojitelji.map(o => (
                  <option key={o.idOdgojitelj} value={o.idOdgojitelj}>
                    {o.idOdgojitelj} {o.ime} {o.prezime}
                  </option>
                ))}
                </select>
              </td>
              <td>
                <input
                  type="date"
                  name="datum"
                  value={newFormData.datum}
                  onChange={handleNewChange}
                />
              </td>
              <td>
                <select name="prisutan" value={newFormData.prisutan} onChange={handleNewChange}>
                  <option value="t">Da</option>
                  <option value="f">Ne</option>
                </select>
              </td>
              <td>
                <input
                  type="text"
                  name="program"
                  value={newFormData.program}
                  onChange={handleNewChange}
                  placeholder="Program"
                />
              </td>
              <td>
                <input
                  type="text"
                  name="napomena"
                  value={newFormData.napomena}
                  onChange={handleNewChange}
                  placeholder="Napomena"
                />
              </td>
              <td className="action-buttons">
                <button className="edit" onClick={saveNew}>💾</button>
                <button className="delete" onClick={cancelNew}>✖️</button>
              </td>
            </tr>
          )}

          {evidencija.map(item => (
            <tr key={item.idEvidencijskaLista}>
              <td>{item.idEvidencijskaLista}</td>
              <td>
                {editId === item.idEvidencijskaLista ? (
                  <select
                    name="idOdgojitelj"
                    value={editFormData.idOdgojitelj}
                    onChange={handleChange}
                  >
                    {odgojitelji.map(o => (
                      <option key={o.idOdgojitelj} value={o.idOdgojitelj}>
                        {o.idOdgojitelj} {o.ime} {o.prezime}
                      </option>
                    ))}
                  </select>
                ) : 
                  item.idOdgojitelj
                }
              </td>
              <td>
                {editId === item.idEvidencijskaLista ? (
                  <input
                    type="date"
                    name="datum"
                    value={editFormData.datum}
                    onChange={handleChange}
                  />
                ) : (
                  item.datum
                )}
              </td>
              <td>
                {editId === item.idEvidencijskaLista ? (
                  <select name="prisutan" value={editFormData.prisutan} onChange={handleChange}>
                    <option value="t">Da</option>
                    <option value="f">Ne</option>
                  </select>
                ) : (
                  item.prisutan ? 'Da' : 'Ne'
                )}
              </td>
              <td>
                {editId === item.idEvidencijskaLista ? (
                  <input
                    type="text"
                    name="program"
                    value={editFormData.program || ''}
                    onChange={handleChange}
                  />
                ) : (
                  item.program
                )}
              </td>
              <td className="napomena-cell">
                {editId === item.idEvidencijskaLista ? (
                  <input
                    type="text"
                    name="napomena"
                    value={editFormData.napomena || ''}
                    onChange={handleChange}
                  />
                ) : (
                  item.napomena
                )}
              </td>
              <td className="action-buttons">
                {editId === item.idEvidencijskaLista ? (
                  <>
                    <button className="edit" onClick={saveEdit}>💾</button>
                    <button className="delete" onClick={cancelEditing}>✖️</button>
                  </>
                ) : (
                  <>
                    <button className="edit" onClick={() => startEditing(item)}>✏️</button>
                    <button className="delete" onClick={() => handleDeleteEvidencija(item.idEvidencijskaLista)}>🗑️</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EvidencijskaListaTablica;