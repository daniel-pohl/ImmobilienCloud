import './App.css'
import CompanyCard from "./CompanyCard/CompanyCard.tsx";
import {Route, Routes} from "react-router-dom";
import CompanyDetail from "./CompanyDetail/CompanyDetail.tsx";
import MainPage from "./MainPage/MainPage.tsx";
import CompanyCreate from "./CompanyCreate/CompanyCreate.tsx";
import ContactDetail from "./ContactDetail/ContactDetail.tsx";
import ContactCard from "./ContactCard/ContactCard.tsx";
import ContactCreate from "./ContactCreate/ContactCreate.tsx";
import LoginPage from "./LoginPage/LoginPage.tsx";
import ProtectedRoute from "./ProtectedRoute.tsx";
import {useEffect, useState} from "react";
import axios from "axios";

function App() {

    const [user, setUser]
        = useState<string>()

    useEffect(() => {
        function getUser() {
            axios.get("/api/users/me")
                .then(response => {
                    setUser(response.data);
                    console.log(response.data);
                })
                .catch(() => {
                    setUser(undefined);
                });
        }
        getUser();
    }, []);

  return (

      <Routes>
          <Route path="/login" element={<LoginPage />} />

          <Route element={<ProtectedRoute user={user}/>}>

              <Route path="/" element={<MainPage />} />
              <Route path="/company/:id" element={<CompanyDetail />} />
              <Route path="/company" element={<CompanyCard />} />
              <Route path="/companycreate" element={<CompanyCreate />} />

              <Route path="/contact/:id" element={<ContactDetail />} />
              <Route path="/contact" element={<ContactCard />} />
              <Route path="/contactcreate" element={<ContactCreate />} />

          </Route>
      </Routes>
  )
}

export default App
