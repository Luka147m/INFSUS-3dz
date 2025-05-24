import { useEffect, useState } from 'react';
import {
  getDjecaIds,
  getDijeteById,
  updateDijete,
  deleteDijeteById,
  getRoditelji,
  getEvidencijaByDijeteId,
  deleteEvidencija,
  updateEvidencija,
  createEvidencija

} from '../api';

import '../styles/MasterDetailPage.css';

import Navigator from '../components/Navigator';
import Dijete from '../components/Dijete';
import EvidencijskaListaTable from '../components/EvidencijskaListaTablica';


const MasterDetailPage = () => {
  const [ids, setIds] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [dijete, setDijete] = useState(null);
  const [evidencija, setEvidencija] = useState([]);
  const [formData, setFormData] = useState(null);
  const [roditelji, setRoditelji] = useState([]);
  const [editingEvidencija, setEditingEvidencija] = useState(null);


  const loadData = async (id) => {
    try {
      const dijeteData = await getDijeteById(id);
      const mapped = {
        ...dijeteData,
        roditelj1Id: dijeteData.imenik?.roditelj1?.idRoditelj || '',
        roditelj2Id: dijeteData.imenik?.roditelj2?.idRoditelj || ''
      };
      setDijete(dijeteData);
      setFormData(mapped);
    } catch (err) {
      console.error('Greška prilikom dohvaćanja djeteta:', err);
      setDijete(null);
      setFormData(null);
    }

    try {
      const evidencijaData = await getEvidencijaByDijeteId(id);
      setEvidencija(evidencijaData);
    } catch (err) {
      console.error('Greška prilikom dohvaćanja evidencije:', err);
      setEvidencija([]);
    }
  };

  useEffect(() => {
    getDjecaIds()
      .then(data => {
        setIds(data);
        if (data.length > 0) {
          loadData(data[0]);
        }
      })
      .catch(err => console.error('Greška prilikom dohvaćanja ID-jeva:', err));
  }, []);

  useEffect(() => {
    if (ids.length > 0) {
      loadData(ids[currentIndex]);
    }
  }, [currentIndex, ids]);

  useEffect(() => {
    getRoditelji()
      .then(data => setRoditelji(data))
      .catch(err => console.error('Greška prilikom dohvaćanja roditelja:', err));
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSave = async () => {
    try {
      const { roditelj1Id, roditelj2Id, ...rest } = formData; // izdvoji nepotrebna polja

       const toSave = {
          idDijete: formData.idDijete,
          idSkupina: formData.idSkupina || 1, // ili koji već treba
          ime: formData.ime,
          prezime: formData.prezime,
          oib: formData.oib,
          mjestoRodenja: formData.mjestoRodenja,
          adresaStanovanja: formData.adresaStanovanja,
          mbo: formData.mbo,
          datumRodenja: formData.datumRodenja,
          idRoditelj1: Number(formData.roditelj1Id) || null,
          idRoditelj2: Number(formData.roditelj2Id) || null,
        };
      console.log(toSave)
      await updateDijete(toSave);
      setDijete(toSave);
    } catch (err) {
      console.error(err);
    }
  };

  const handleCancel = () => {
    setFormData({
      ...dijete,
      roditelj1Id: dijete.imenik?.roditelj1?.idRoditelj || '',
      roditelj2Id: dijete.imenik?.roditelj2?.idRoditelj || ''
    });
  };

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

  const handleEditEvidencija = (item) => {
    setEditingEvidencija(item);
  };

const handleSaveEvidencija = async (updated) => {
  try {
    // Pretpostavljam da imaš API funkciju za update evidencije:
    await updateEvidencija(updated);

    setEvidencija(prev => prev.map(el =>
      el.idEvidencijskaLista === updated.idEvidencijskaLista ? updated : el
    ));
    setEditingEvidencija(null);
  } catch (err) {
    console.error('Greška pri spremanju evidencije:', err);
  }
};

const handleCancelEdit = () => {
  setEditingEvidencija(null);
};  

const handleDeleteEvidencija = (id) => {
              deleteEvidencija(id)
                .then(() => setEvidencija(prev => prev.filter(el => el.idEvidencijskaLista !== id)))
                .catch(err => console.error('Greška pri brisanju:', err));
}

const handleAddEvidencija = async (newRecord) => {
  try {
     const maxId = evidencija.reduce((max, el) => (el.idEvidencijskaLista > max ? el.idEvidencijskaLista : max), 0);
    const recordWithId = { ...newRecord, idEvidencijskaLista: maxId + 1 };
    const createdRecord = await createEvidencija(recordWithId);
    setEvidencija(prev => [...prev, createdRecord]);
  } catch (err) {
    console.error('Greška pri dodavanju novog zapisa:', err);
  }
};


  if (!formData) return <p>Učitavanje...</p>;

  return (
    <div className="master-page">
      <Navigator ids={ids} currentIndex={currentIndex} setCurrentIndex={setCurrentIndex} />

      <section className="dijete-section">
        <h2>Dijete #{ids[currentIndex]}</h2>
        <Dijete
          formData={formData}
          roditelji={roditelji}
          handleChange={handleChange}
          handleSave={handleSave}
          handleCancel={handleCancel}
          onDelete={handleDelete}
        />
      </section>

      <hr className="divider" />

      <section className="evidencija-section">
        <h3>Evidencijska lista</h3>
        <EvidencijskaListaTable
            lista={evidencija}
            onEdit={handleEditEvidencija}
            onDelete={handleDeleteEvidencija}
            onSave={handleSaveEvidencija}
            onAdd={handleAddEvidencija}
        />
      </section>
    </div>
  );
};

export default MasterDetailPage;
