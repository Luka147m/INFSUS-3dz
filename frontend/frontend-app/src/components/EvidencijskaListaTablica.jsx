import React, { useState } from 'react';

const EvidencijskaListaTablica = ({ lista, onEdit, onDelete, onSave, onAdd }) => {
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

  const startEditing = (item) => {
    setEditId(item.idEvidencijskaLista);
    setEditFormData({
      ...item,
      prisutan: item.prisutan ? 't' : 'f',
    });
  };

  const cancelEditing = () => {
    setEditId(null);
    setEditFormData({});
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditFormData(prev => ({ ...prev, [name]: value }));
  };

  const saveEdit = () => {
    const updated = {
      ...editFormData,
      prisutan: editFormData.prisutan === 't',
    };
    onSave(updated);
    setEditId(null);
    setEditFormData({});
  };

  const handleNewChange = (e) => {
    const { name, value } = e.target;
    setNewFormData(prev => ({ ...prev, [name]: value }));
  };

  const saveNew = () => {
    // Pretvori prisutan u boolean
    const newRecord = {
      ...newFormData,
      prisutan: newFormData.prisutan === 't',
    };
    onAdd(newRecord);
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
    setNewFormData({
      idOdgojitelj: '',
      datum: '',
      prisutan: 't',
      program: '',
      napomena: ''
    });
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
                <input
                  type="text"
                  name="idOdgojitelj"
                  value={newFormData.idOdgojitelj}
                  onChange={handleNewChange}
                  placeholder="ID odgojitelja"
                />
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

          {lista.map(item => (
            <tr key={item.idEvidencijskaLista}>
              <td>{item.idEvidencijskaLista}</td>
              <td>{item.idOdgojitelj}</td>
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
              <td>
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
                    <button className="delete" onClick={() => onDelete(item.idEvidencijskaLista)}>🗑️</button>
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
