import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import MasterDetailPage from './pages/MasterDetailPage';
import SifrarnikPage from './pages/SifrarnikPage';
import Navbar from './components/Navbar';
import logo from './logo.svg';
import './App.css';

import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/masterdetail" element={<MasterDetailPage />} />
        <Route path="/sifrarnik" element={<SifrarnikPage />} />
      </Routes>
      <ToastContainer position="top-right" autoClose={3000} hideProgressBar />
    </Router>
  );
}

export default App;
