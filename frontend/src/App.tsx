import './App.css'
import CompanyCard from "./CompanyCard/CompanyCard.tsx";
import {Route, Routes} from "react-router-dom";
import CompanyDetail from "./CompanyDetail/CompanyDetail.tsx";
import CompanyCreate from "./CompanyCreate/CompanyCreate.tsx";

function App() {


  return (

      <Routes>
          <Route path="/" element={<CompanyCard />} />
          <Route path="/company/:id" element={<CompanyDetail />} />
          <Route path="/company" element={<CompanyCreate />} />

      </Routes>
  )
}

export default App
