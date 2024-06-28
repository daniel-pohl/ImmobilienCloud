import './App.css'
import CompanyCard from "./CompanyCard/CompanyCard.tsx";
import {Route, Routes} from "react-router-dom";
import CompanyDetail from "./CompanyDetail/CompanyDetail.tsx";
import MainPage from "./MainPage/MainPage.tsx";
import CompanyCreate from "./CompanyCreate/CompanyCreate.tsx";
import ContactDetail from "./ContactDetail/ContactDetail.tsx";
import ContactCard from "./ContactCard/ContactCard.tsx";
import ContactCreate from "./ContactCreate/ContactCreate.tsx";


function App() {


  return (

      <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/company/:id" element={<CompanyDetail />} />
          <Route path="/company" element={<CompanyCard />} />
          <Route path="/companycreate" element={<CompanyCreate />} />

          <Route path="/contact/:id" element={<ContactDetail />} />
          <Route path="/contact" element={<ContactCard />} />
          <Route path="/contactcreate" element={<ContactCreate />} />
      </Routes>
  )
}

export default App
