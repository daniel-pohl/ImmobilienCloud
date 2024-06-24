import React, {useState} from 'react';
import {Link, useNavigate} from 'react-router-dom';
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
    const navigate = useNavigate();

    const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const response = await axios.post('/api/company', formData).then();

        console.log('Response:', response.data);
        navigate('/company');
        alert('Company created successfully');
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
            <Link to={`/company`}>
                <h2>Back</h2>
            </Link>
            <br/>

            <h2>Neue Firma erstellen</h2>
            <form onSubmit={handleSubmit}>
                <label>Name:
                    <input type="text" name="name" value={formData.name} onChange={handleChange} required/>
                    <br/>
                </label>
                <label>Land:
                    <input type="text" name="country" value={formData.country} onChange={handleChange} required/>
                    <br/>
                </label>
                <label>Stadt:
                    <input type="text" name="city" value={formData.city} onChange={handleChange} required/>
                    <br/>
                </label>
                <label>PLZ:
                    <input type="text" name="plz" value={formData.plz} onChange={handleChange} required/>
                    <br/>
                </label>
                <label>Straße:
                    <input type="text" name="street" value={formData.street} onChange={handleChange} required/>
                    <br/>
                </label>
                <label>Hausnummer:
                    <input type="text" name="streetNumber" value={formData.streetNumber} onChange={handleChange}/>
                    <br/>
                </label>
                <label>Telefonnummer:
                    <input type="text" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange}/>
                    <br/>
                </label>
                <label>E-Mail:
                    <input type="email" name="email" value={formData.email} onChange={handleChange}/>
                    <br/>
                </label>
                <label>Website:
                    <input type="url" name="website" value={formData.website} onChange={handleChange}/>
                    <br/>
                </label>
                <label>Kommentar:
                    <textarea name="comment" value={formData.comment} onChange={handleChange}/>
                    <br/>
                </label>
                <button type="submit">Firma erstellen</button>
            </form>
        </div>
    );
}

export default CompanyCreate;
