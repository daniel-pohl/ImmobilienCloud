import Sidebar from "../Sidebar/Sidebar.tsx";
import Header from "../Header/Header.tsx";
import React, { useState } from 'react';
import {Company} from "../CompanyCard/Company.ts";
import './MainPage.css'
import {Link} from "react-router-dom";
import axios from "axios";

function MainPage() {

    const [searchTerm, setSearchTerm] = useState("");
    const [companies, setCompanies] = useState<Company[]>([]);


    function handleSearch(event: React.FormEvent) {
        event.preventDefault();

        const encodedSearchTerm = encodeURIComponent(searchTerm);

        axios.get(`/api/company/search?name=${encodedSearchTerm}`)
            .then(response => {
                setCompanies(response.data);
            });
    }

    return (
        <div className="page-container">
            <Header />
            <div className="sidebar-container">
                <Sidebar />
                <div className="div-list">
                    <form onSubmit={handleSearch}>
                        <li>
                            <input
                                type="text"
                                className="search-bar"
                                placeholder="Suche..."
                                value={searchTerm}
                                onChange={(e) => setSearchTerm(e.target.value)}
                            />
                            <button type="submit">Suche</button>
                        </li>
                    </form>

                    {companies.map(company => (
                        <li className="company-list-item" key={company.id}>
                            <h2>{company.name}</h2>
                            <Link to={`/company/${company.id}`}>
                                <h3>Details</h3>
                            </Link>
                        </li>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default MainPage;