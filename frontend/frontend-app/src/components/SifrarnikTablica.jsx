import { useEffect, useState } from 'react';
import {getRoditelji, createRoditelj, deleteRoditelj, updateRoditelj} from '../api';

import '../styles/SifrarnikTablica.css';

const SifrarnikTablica = ()=> {
    const [roditelji, setRoditelji] = useState([]);
    const [editId, setEditId] = useState(null);
    const [isAdding, setIsAdding] = useState(false);
    const [newFormData, setNewFormData] = useState({
        ime: '',
        prezime: '',
        email: '',
        brojTelefona: '',
        lozinka: '',
        uloga: 'Roditelj',
        zanimanje: '',
        radnoMjesto: '',
        oib: ''
    });
    const [updateFormData, setUpdateFormData] = useState({
        ime: '',
        prezime: '',
        zanimanje: '',
        radnoMjesto: '',
        oib: ''
    });
    const [error, setError] = useState(null);

    useEffect(() => {
        getRoditelji()
          .then(data => setRoditelji(data))
          .catch(err => setError('Greška prilikom dohvaćanja roditelja.'));
      }, []);

    useEffect(() => {
      if (error) {
        const timer = setTimeout(() => setError(null), 5000);
        return () => clearTimeout(timer);
      }
    }, [error]);

    const handleNewChange = (e) => {
      const { name, value } = e.target;
      setNewFormData(prev => ({ ...prev, [name]: value }));
    };


    const validateImePrezime = (value) => /^[A-Za-zčćžšđČĆŽŠĐ-]{1,50}$/.test(value);
    const validateEmail = (value) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);
    const validateLozinka = (value) => /^[^\s]{8,40}$/.test(value);
    const validateZanimanje = (value) => /^[A-Za-zčćžšđČĆŽŠĐ ]{1,40}$/.test(value);
    const validateRadnoMjesto = (value) => /^[A-Za-zčćžšđČĆŽŠĐ0-9 ]{1,50}$/.test(value);
    const validateOIB = (value) => /^\d{11}$/.test(value);
    const validateBrojTelefona = (value) => /^\+?\d{6,20}$/.test(value);
    
    const validateForm = (form) => {
      if (!validateImePrezime(form.ime)) return 'Ime smije sadržavati samo slova i crticu, max 50 znakova.';
      if (!validateImePrezime(form.prezime)) return 'Prezime smije sadržavati samo slova i crticu, max 50 znakova.';
      if (!validateEmail(form.email)) return 'Email nije ispravan.';
      if (!validateBrojTelefona(form.brojTelefona)) return 'Broj telefona smije sadržavati samo brojeve i +, 6-20 znamenki.';
      if (!validateLozinka(form.lozinka)) return 'Lozinka mora imati barem 8 znakova, slova, broj i specijalni znak.';
      if (!validateZanimanje(form.zanimanje)) return 'Zanimanje smije sadržavati samo slova, max 40 znakova.';
      if (!validateRadnoMjesto(form.radnoMjesto)) return 'Radno mjesto smije sadržavati slova i brojeve, max 50 znakova.';
      if (!validateOIB(form.oib)) return 'OIB mora imati točno 11 znamenki.';
      return null;
    };
    
    const validateEditForm = (form) => {
      if (!validateImePrezime(form.ime)) return 'Ime smije sadržavati samo slova i crticu, max 50 znakova.';
      if (!validateImePrezime(form.prezime)) return 'Prezime smije sadržavati samo slova i crticu, max 50 znakova.';
      if (!validateZanimanje(form.zanimanje)) return 'Zanimanje smije sadržavati samo slova, max 40 znakova.';
      if (!validateRadnoMjesto(form.radnoMjesto)) return 'Radno mjesto smije sadržavati slova i brojeve, max 50 znakova.';
      if (!validateOIB(form.oib)) return 'OIB mora imati točno 11 znamenki.';
      return null;
    };

    const saveNew = async (e) => {
      e.preventDefault();
      const validationError = validateForm(newFormData);
      if (validationError) {
        setError(validationError);
        return;
      }
      try {
        await createRoditelj(newFormData);
        setIsAdding(false);
        setNewFormData({
          ime: '', prezime: '', email: '', brojTelefona: '', lozinka: '', uloga: 'Roditelj', zanimanje: '', radnoMjesto: '', oib: ''
        });
        setError(null);
        const data = await getRoditelji();
        setRoditelji(data);
      } catch (err) {
        setError('Greška prilikom spremanja roditelja.');
      }
    };

    const cancelNew = () => {
      setIsAdding(false);
      setNewFormData({
        ime: '', prezime: '', email: '', brojTelefona: '', lozinka: '', uloga: 'Roditelj', zanimanje: '', radnoMjesto: '', oib: ''
      });
      setError(null);
    };

    const handleDelete = async (id) => {
      try {
        await deleteRoditelj(id);
        setRoditelji(prev => prev.filter(r => r.idRoditelj !== id));
        setError(null);
      } catch (err) {
        setError('Greška prilikom brisanja roditelja.');
      }
    };

    const handleEditClick = (roditelj) => {
      setEditId(roditelj.idRoditelj);
      setUpdateFormData({
        ime: roditelj.ime || '',
        prezime: roditelj.prezime || '',
        zanimanje: roditelj.zanimanje || '',
        radnoMjesto: roditelj.radnoMjesto || '',
        oib: roditelj.oib || ''
      });
    };

    const handleUpdateChange = (e) => {
      const { name, value } = e.target;
      setUpdateFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleUpdateSave = async (id) => {
      const validationError = validateEditForm(updateFormData);
      if (validationError) {
        setError(validationError);
        return;
      }
      try {
        const updated = {...updateFormData};
        await updateRoditelj(id, updated);
        setRoditelji(prev => prev.map(r => r.idRoditelj === id ? { ...r, ...updated } : r));
        setEditId(null);
        setError(null);
      } catch (err) {
        setError('Greška prilikom ažuriranja roditelja.');
      }
    };

    const handleUpdateCancel = () => {
      setEditId(null);
      setUpdateFormData({
        ime: '',
        prezime: '',
        zanimanje: '',
        radnoMjesto: '',
        oib: ''
      });
    };

    return (
    <div className='sifrarnik-container'>
      {error && <div style={{color:'red', marginBottom:8}}>{error}</div>}
      {!isAdding && (
        <button
          style={{ marginBottom: '10px', padding: '6px 12px', borderRadius: '5px', cursor: 'pointer' }}
          onClick={() => {
            setIsAdding(true);
            setNewFormData(prev => ({ ...prev, uloga: 'Roditelj' }));
          }}
          disabled={editId !== null}
        >
          ➕ Dodaj novog roditelja
        </button>
      )}
      {isAdding && (
        <div className='form-grid'>
        <form onSubmit={saveNew} style={{marginBottom: '20px', border: '1px solid #ccc', padding: '16px', borderRadius: '8px', background: '#f9f9f9'}}>
          <div style={{display:'flex', flexWrap:'wrap', gap:'12px'}}>
            <label>
              Ime:
              <input type="text" name="ime" value={newFormData.ime} onChange={handleNewChange} placeholder="Ime" required />
            </label>
            <label>
              Prezime:
              <input type="text" name="prezime" value={newFormData.prezime} onChange={handleNewChange} placeholder="Prezime" required />
            </label>
            <label>
              Email:
              <input
                type="email"
                name="email"
                value={newFormData.email}
                onChange={handleNewChange}
                placeholder="Email"
                required
                aria-invalid={!!error && error.toLowerCase().includes('email')}
              />
              {error && error.toLowerCase().includes('email') && (
                <span style={{color:'red', fontSize:'0.85em'}}>{error}</span>
              )}
            </label>
            <label>
              Broj telefona:
              <input type="text" name="brojTelefona" value={newFormData.brojTelefona} onChange={handleNewChange} placeholder="Broj telefona" required />
            </label>
            <label>
              Lozinka:
              <input type="password" name="lozinka" value={newFormData.lozinka} onChange={handleNewChange} placeholder="Lozinka" required />
            </label>
            <label>
              Uloga:
              <input type="text" name="uloga" value="Roditelj" readOnly style={{background:'#eee', color:'#888'}} required />
            </label>
            <label>
              Zanimanje:
              <input type="text" name="zanimanje" value={newFormData.zanimanje} onChange={handleNewChange} placeholder="Zanimanje" required />
            </label>
            <label>
              Radno mjesto:
              <input type="text" name="radnoMjesto" value={newFormData.radnoMjesto} onChange={handleNewChange} placeholder="Radno mjesto" required />
            </label>
            <label>
              OIB:
              <input type="text" name="oib" value={newFormData.oib} onChange={handleNewChange} placeholder="OIB" required />
            </label>
          </div>
          <div style={{marginTop:'12px'}}>
            <button type="submit" className="button save">💾 Spremi</button>
            <button type="button" className="button cancel" onClick={cancelNew} style={{marginLeft:'8px'}}>✖️ Odustani</button>
          </div>
        </form>
        </div>
      )}
      <table className="sifrarnik-tablica">
        <thead>
          <tr>
            <th>ID</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Zanimanje</th>
            <th>Radno mjesto</th>
            <th>OIB</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {roditelji.map(roditelj => (
            <tr key={roditelj.idRoditelj}>
              <td>{roditelj.idRoditelj}</td>
              <td>{editId === roditelj.idRoditelj ? (
                <input type="text" name="ime" value={updateFormData.ime} onChange={handleUpdateChange} />
              ) : roditelj.ime}</td>
              <td>{editId === roditelj.idRoditelj ? (
                <input type="text" name="prezime" value={updateFormData.prezime} onChange={handleUpdateChange} />
              ) : roditelj.prezime}</td>
              <td>{editId === roditelj.idRoditelj ? (
                <input type="text" name="zanimanje" value={updateFormData.zanimanje} onChange={handleUpdateChange} />
              ) : roditelj.zanimanje}</td>
              <td>{editId === roditelj.idRoditelj ? (
                <input type="text" name="radnoMjesto" value={updateFormData.radnoMjesto} onChange={handleUpdateChange} />
              ) : roditelj.radnoMjesto}</td>
              <td>{editId === roditelj.idRoditelj ? (
                <input type="text" name="oib" value={updateFormData.oib} onChange={handleUpdateChange} />
              ) : roditelj.oib}</td>
              <td className="action-buttons">
                {editId === roditelj.idRoditelj ? (
                  <>
                    <button className="edit" onClick={() => handleUpdateSave(roditelj.idRoditelj)}>💾</button>
                    <button className="delete" onClick={handleUpdateCancel}>✖️</button>
                  </>
                ) : (
                  <>
                    <button className="edit" onClick={() => handleEditClick(roditelj)}>✏️</button>
                    <button className="delete" onClick={() => handleDelete(roditelj.idRoditelj)}>🗑️</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
       </table>
      </div>
      );
}
export default SifrarnikTablica;