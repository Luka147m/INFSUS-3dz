import SifrarnikTablica from "../components/SifrarnikTablica";
import '../styles/SifrarnikPage.css';

const SifrarnikPage = () => {

return (
    <div className="sifrarnik-page">
        <section className="sifrarnik-section">
            <h2>Šifrarnik roditelja</h2>
            <SifrarnikTablica />
        </section>
    </div>
    );
};
export default SifrarnikPage;