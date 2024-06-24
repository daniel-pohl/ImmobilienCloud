import { useEffect, useState } from 'react';
import axios from 'axios';
import {Company} from "./Company.ts";
import './CompanyCard.css';
import {Link} from "react-router-dom";


function CompanyCard() {

    const [companies, setCompanies] = useState<Company[]>([]);



    function fetchCompanies(){

        axios.get("/api/company")
            .then(response =>{
                setCompanies(response.data)
            })
    }

    useEffect(fetchCompanies,[])

    if(!companies){
        return "Lade...."
    }

    return (
        <div className="div-company-list">
            <Link to="/company">
                <button className="buttonCreate">Neue Firma anlegen</button>
            </Link>
            <h1>Liste aller Firmen:</h1>
            <ul>
                {companies.map(company => (
                    <li className="company-list-item" key={company.id}>
                        <h2>{company.name}</h2>
                        <Link to={`/company/${company.id}`}>
                            <h3>Details</h3>
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default CompanyCard;

