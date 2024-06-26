import Sidebar from "../Sidebar/Sidebar.tsx";
import Header from "../Header/Header.tsx";
import React, { useState } from 'react';
import {Company} from "../CompanyCard/Company.ts";
import './MainPage.css'
import {Link} from "react-router-dom";

function MainPage() {

    const [searchTerm, setSearchTerm] = useState("");
    const [companies, setCompanies] = useState<Company[]>([]);
    const [error, setError] = useState<string | null>(null);

    const handleSearch = async (event: React.FormEvent) => {
        event.preventDefault();
        // VerhindertNeuladen der Seite beim Absenden des Formulars

        const response = await fetch(`/api/company/search?name=${encodeURIComponent(searchTerm)}`);
        if (!response.ok) {
            setError("Network response was not ok");
            return;
        }
        const data: Company[] = await response.json();
        setCompanies(data);
        setError(null);
        // Setzt den Fehler zurück, wenn Anfrage erfolgreich

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
                    {error && <li className="error">{error}</li>}
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