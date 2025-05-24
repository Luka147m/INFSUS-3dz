import React from 'react';
import '../styles/Dijete.css';

const Dijete = ({ formData, roditelji, handleChange, handleSave, handleCancel, handleDelete, handleCreate }) => {
  if (!formData) return <p>Odaberi dijete za prikaz detalja.</p>;

  return (
    <div className="dijete-container">
      <div className="form-grid">
        <label>
          Ime:
          <input name="ime" value={formData.ime} onChange={handleChange} />
        </label>

        <label>
          Prezime:
          <input name="prezime" value={formData.prezime} onChange={handleChange} />
        </label>

        <label>
          OIB:
          <input name="oib" value={formData.oib} onChange={handleChange} />
        </label>

        <label>
          Mjesto rođenja:
          <input name="mjestoRodenja" value={formData.mjestoRodenja} onChange={handleChange} />
        </label>

        <label>
          Adresa stanovanja:
          <input name="adresaStanovanja" value={formData.adresaStanovanja} onChange={handleChange} />
        </label>

        <label>
          MBO:
          <input name="mbo" value={formData.mbo} onChange={handleChange} />
        </label>

        <label>
          Datum rođenja:
          <input
            type="date"
            name="datumRodenja"
            value={formData.datumRodenja}
            onChange={handleChange}
          />
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
            onClick={handleCreate}
            style={{ marginLeft: 'auto' }}  // <-- Ovo pomiče gumb Novo dijete skroz desno
          >
            ➕ Novo dijete
          </button>
</div>

    </div>
  );
};

export default Dijete;
