import { useEffect, useState } from 'react';
import {
  getDjecaIds,
  getDijeteById,
  updateDijete,
  createDijete,
  getRoditelji,
  getEvidencijaByDijeteId,
} from '../api';

import '../styles/MasterDetailPage.css';

import Navigator from '../components/Navigator';
import Dijete from '../components/Dijete';
import EvidencijskaListaTable from '../components/EvidencijskaListaTablica';
import { toast } from 'react-toastify';


const MasterDetailPage = () => {
  const [ids, setIds] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [dijete, setDijete] = useState(null);
  const [evidencija, setEvidencija] = useState([]);
  const [formData, setFormData] = useState(null);
  const [roditelji, setRoditelji] = useState([]);
  const [editingEvidencija, setEditingEvidencija] = useState(null);


  const validateImePrezime = (value) => /^[A-Za-zčćžšđČĆŽŠĐ -]{1,50}$/.test(value);
    const validateDatum = (value) => !!value; 
    const validateMjesto = (value) => value && value.length <= 50;
    const validateAdresa = (value) => value && value.length <= 100;
    const validateOIB = (value) => /^\d{11}$/.test(value);
    const validateMBO = (value) => /^\d{9}$/.test(value); 
  
    const validateForm = (form) => {
    if (!validateImePrezime(form.ime)) return 'Ime smije sadržavati samo slova, razmake i crticu, max 50 znakova.';
    if (!validateImePrezime(form.prezime))  return 'Prezime smije sadržavati samo slova, razmake i crticu, max 50 znakova.';
    if (!validateDatum(form.datumRodenja)) return 'Datum rođenja je obavezan.';
    if (!validateMjesto(form.mjestoRodenja)) return 'Mjesto rođenja mora biti ispravno (max 50 znakova).';
    if (!validateAdresa(form.adresaStanovanja)) return 'Adresa mora biti ispravna (max 100 znakova).';
    if (!validateOIB(form.oib)) return 'OIB mora imati točno 11 znamenki.';
    if (!validateMBO(form.mbo)) return 'MBO mora imati točno 9 znamenki.';
    return null; 
  };

  const loadData = async (id) => {
    try {
      const dijeteData = await getDijeteById(id);
      const mapped = {
        ...dijeteData,
        roditelj1Id: dijeteData.imenik?.roditelj1?.idRoditelj || '',
        roditelj2Id: dijeteData.imenik?.roditelj2?.idRoditelj || ''
      };
      setDijete(dijeteData);
      console.log(dijeteData)
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



  const handleSave = async () => {
    if (!formData) {
        console.error('formData je undefined!');
        return;
      }
     const validationError = validateForm(formData);
      if (validationError) {
        toast.error(validationError);
        return;
      }

    try {
      const { roditelj1Id, roditelj2Id, ...rest } = formData; 

       const toSave = {
          idDijete: formData.idDijete,
          idSkupina: formData.idSkupina || 1, 
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
      
        if (!toSave.idDijete) {
          await createDijete(toSave);
          toast.success('Dijete uspješno dodano!');
          const newIds = await getDjecaIds();
          setIds(newIds);
          const newIndex = newIds.length - 1;
          setCurrentIndex(newIndex); 
          await loadData(newIds[newIndex]); 

        } else {
          await updateDijete(toSave);
          toast.success('Dijete uspješno ažurirano!');
          setDijete(toSave);
    }
    } catch (err) {
      console.error(err);
      toast.error('Došlo je do greške prilikom spremanja djeteta.');
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
          dijete={dijete}
          ids={ids}
          setIds={setIds}
          roditelji={roditelji}
          setCurrentIndex={setCurrentIndex}
          setFormData={setFormData}
          setDijete={setDijete}
          handleSave={handleSave}
        />
      </section>

      <hr className="divider" />

      <section className="evidencija-section">
        <h3>Evidencijska lista</h3>
        <EvidencijskaListaTable
            lista={evidencija}
            evidencija={evidencija}
            dijete={dijete}
            setEvidencija={setEvidencija} 
            setEditingEvidencija={setEditingEvidencija}
        />
      </section>
    </div>
  );
};

export default MasterDetailPage;