
import React, {useState} from 'react';

import '../styles/Dijete.css';
import {
 deleteDijeteById,


} from '../api';


const Dijete = ({ formData, dijete, ids, setIds, roditelji, setCurrentIndex, setFormData, setDijete, handleSave }) => {
  
    if (!formData) return <p>Odaberi dijete za prikaz detalja.</p>;

  const handleDelete = async (id) => {
    try {
      await deleteDijeteById(id);
      const newIds = ids.filter(i => i !== id);
      setIds(newIds);
      setCurrentIndex(0);
    } catch (err) {
      console.error(err);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  
  const handleCancel = () => {
    setFormData({
      ...dijete,
      roditelj1Id: dijete.imenik?.roditelj1?.idRoditelj || '',
      roditelj2Id: dijete.imenik?.roditelj2?.idRoditelj || ''
    });
  };

   const handleCreateDijete = async () => {
    const novo = {
      idDijete: null,
      ime: '',
      prezime: '',
      oib: '',
      datumRodenja: '',
      mjestoRodenja: '',
      adresaStanovanja: '',
      mbo: '',
      idSkupina: '',
      roditelj1Id: '',
      roditelj2Id: '',
    };
    setFormData(novo);
    setDijete(null); 
  }

  

  return (
    <div className="dijete-container">
      <div className="form-grid">
        <label>
          Ime:
          <input name="ime" value={formData.ime} onChange={handleChange} placeholder="Ime" required />
        </label>

        <label>
          Prezime:
          <input name="prezime" value={formData.prezime} onChange={handleChange} placeholder="Prezime" required  />
        </label>

         <label>
          Datum rođenja:
          <input
            type="date"
            name="datumRodenja"
            value={formData.datumRodenja}
            onChange={handleChange}
            placeholder="Datum rođenja" required
          />
        </label>

        <label>
          Mjesto rođenja:
          <input name="mjestoRodenja" value={formData.mjestoRodenja} onChange={handleChange} placeholder="Mjesto rođenja" required/>
        </label>
        
        <label>
          Adresa stanovanja:
          <input name="adresaStanovanja" value={formData.adresaStanovanja} onChange={handleChange} placeholder="Adresa stanovanja" required/>
        </label>
        
        <label>
          OIB:
          <input name="oib" value={formData.oib} onChange={handleChange} placeholder="OIB" required/>
        </label>

        <label>
          MBO:
          <input name="mbo" value={formData.mbo} onChange={handleChange} placeholder="MBO" required/>
        </label>
        
        <label>
          Roditelj 1:
          <select name="roditelj1Id" value={formData.roditelj1Id || ''} onChange={handleChange}>
            <option value="">-- Odaberi roditelja --</option>
            {roditelji.map(r => (
              <option key={r.idRoditelj} value={r.idRoditelj}>
                {r.ime} {r.prezime}
              </option>
            ))}
          </select>
        </label>

        <label>
          Roditelj 2:
          <select name="roditelj2Id" value={formData.roditelj2Id || ''} onChange={handleChange}>
            <option value="">-- Odaberi roditelja --</option>
            {roditelji.map(r => (
              <option key={r.idRoditelj} value={r.idRoditelj}>
                {r.ime} {r.prezime}
              </option>
            ))}
          </select>
        </label>
      </div>

      <div className="button-group">
          <button className="button save" onClick={handleSave}>
            Spremi
          </button>
          <button className="button cancel" onClick={handleCancel}>
            Odustani
          </button>
          <button
            className="button delete"
            onClick={() => handleDelete(formData.idDijete)}
          >
            Obriši dijete
          </button>

          <button
            className="button new"
            onClick={handleCreateDijete}
            style={{ marginLeft: 'auto' }}
          >
            ➕ Novo dijete
          </button>
      </div>
    </div>
  );
};

export default Dijete;