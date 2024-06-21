import React, {useState} from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

function CompanyCreate() {

    const [formData, setFormData]
        = useState({
        name: '',
        country: '',
        city: '',
        plz: '',
        street: '',
        streetNumber: '',
        phoneNumber: '',
        email: '',
        website: '',
        comment: ''
    });


    const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const response = await axios.post('/api/company', formData);
        console.log('Response:', response.data);
        setFormData({  // Reset the form fields to empty after successful submission
            name: '',
            country: '',
            city: '',
            plz: '',
            street: '',
            streetNumber: '',
            phoneNumber: '',
            email: '',
            website: '',
            comment: ''
        });

    };

    return (
        <div>
            <Link to={`/`}>
                <h2>Back</h2>
            </Link>
            <br/>

            <h2>Neue Firma erstellen</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor={"name"}>Name:</label>
                <input id={"name"} type="text" name="name" value={formData.name} onChange={handleChange} required/><br/>

                <label>Land:
                    <input type="text" name="country" value={formData.country} onChange={handleChange} required/>
                    <br/>
                </label>


                <label>Stadt:</label>
                <input type="text" name="city" value={formData.city} onChange={handleChange} required/><br/>

                <label>PLZ:</label>
                <input type="text" name="plz" value={formData.plz} onChange={handleChange} required/><br/>

                <label>Straße:</label>
                <input type="text" name="street" value={formData.street} onChange={handleChange} required/><br/>

                <label>Hausnummer:</label>
                <input type="text" name="streetNumber" value={formData.streetNumber} onChange={handleChange}/><br/>

                <label>Telefonnummer:</label>
                <input type="text" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange}/><br/>

                <label>E-Mail:</label>
                <input type="email" name="email" value={formData.email} onChange={handleChange}/><br/>

                <label>Website:</label>
                <input type="url" name="website" value={formData.website} onChange={handleChange}/><br/>

                <label>Kommentar:</label>
                <textarea name="comment" value={formData.comment} onChange={handleChange}/><br/>

                <button type="submit">Firma erstellen</button>
            </form>
        </div>
    );
}

export default CompanyCreate;
