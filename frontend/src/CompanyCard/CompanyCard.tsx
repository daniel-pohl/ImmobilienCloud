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

            <h1>Liste aller Firmen:</h1>
            <ul>
                {companies.map(company => (
                    <li className="company-list-item" key={company.id}>
                        <Link to={`/company/${company.id}`}>
                            <h2>Link to Details</h2>
                        </Link>
                        <h2>{company.name}</h2>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default CompanyCard;

