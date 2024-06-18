import { useEffect, useState } from 'react';
import axios from 'axios';
import {Company} from "./Company.ts";
import './CompanyCard.css';

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
            <h1>Company List</h1>
            <ul>
                {companies.map(company => (
                    <li className="company-list-item" key={company.id}>
                        <h2>{company.name}</h2>
                        <p>{company.street} {company.streetNumber}, {company.plz} {company.city}, {company.country}</p>
                        <p>{company.phoneNumber}</p>
                        <p>{company.email}</p>
                        <p>{company.website}</p>
                        <p>{company.comment}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default CompanyCard;
