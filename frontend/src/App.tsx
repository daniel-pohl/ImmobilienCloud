import './App.css'
import CompanyCard from "./CompanyCard/CompanyCard.tsx";
import {Route, Routes} from "react-router-dom";
import CompanyDetail from "./CompanyDetail/CompanyDetail.tsx";

function App() {


  return (

      <Routes>
          <Route path="/" element={<CompanyCard />} />
          <Route path="/company/:id" element={<CompanyDetail />} />
      </Routes>
  )
}

export default App
