import { Link } from 'react-router-dom';
import '../styles/Navbar.css';

const Navbar = () => (
   <nav className="navbar">
    <ul className="nav-links">
      <li><Link to="/">Početna</Link></li>
      <li><Link to="/masterdetail">Master-detail</Link></li>
      <li><Link to="/sifrarnik">Šifrarnik</Link></li>
    </ul>
  </nav>
);

export default Navbar;
