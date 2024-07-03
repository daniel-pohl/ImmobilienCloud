import { useEffect, useState } from 'react';
import axios from 'axios';
import {Contact} from "./Contact.ts";
import './ContactCard.css';
import {Link} from "react-router-dom";
import Header from "../Header/Header.tsx";
import Sidebar from "../Sidebar/Sidebar.tsx";

function ContactCard() {

    const [contacts, setContacts] = useState<Contact[]>([]);

    function fetchContacts(){
        axios.get("/api/contact")
            .then(response => {
                setContacts(response.data)
            });
    }

    useEffect(fetchContacts,[])

    if(!contacts){
        return "Lade...."
    }

    function handleFavoriteClick(id: string) {
        axios.post(`/api/contact/${id}/favorite`)
            .then(() => {
                fetchContacts();
            })
            .catch(error => {
                console.error("There was an error toggling the favorite status!", error);
            });
    }

    return (
        <div className="page-container">
            <Header />
            <div className="sidebar-container">
                <Sidebar/>
                <div className="div-contact-list">
                    <Link to="/contactcreate">
                        <button className="buttonCreate">Neuen Kontakt anlegen</button>
                    </Link>
                    <h1>Liste aller Kontakte:</h1>
                    <ul>
                        {contacts.map(contact => (
                            <li className="contact-list-item" key={contact.id}>
                                <h2>{contact.name}</h2>
                                <Link to={`/contact/${contact.id}`}>
                                    <h3>Details</h3>
                                </Link>
                                <button className="favorite-button" onClick={() => handleFavoriteClick(contact.id)}>
                                    {contact.favorite ? "💚" : "🤍"}
                                </button>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default ContactCard;

