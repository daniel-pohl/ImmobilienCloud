import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import axios from 'axios';
import './CompanyDetail.css';
import {Company} from "../CompanyCard/Company.ts";

function CompanyDetail() {
    const {id} = useParams<{ id: string }>();
    const [formData, setFormData] = useState<Company | null>(null);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`/api/company/${id}`)
            .then(response => {
                setFormData(response.data);
            })
            .catch(() => {
                setFormData(null);
            });
    }, [id]);

    const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const {name, value} = event.target;
        if (formData) {
            setFormData({...formData, [name]: value});
        }
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (formData) {
            try {
                const response = await axios.put(`/api/company/${id}`, formData);
                console.log('Response:', response.data);
                navigate('/');
                alert('Company updated successfully');
            } catch (error) {
                console.error('Error updating company:', error);
            }
        }
    };

    const handleDelete = () => {
        axios.delete(`/api/company/${id}`)
            .then(() => {
                navigate('/');
                alert(`Company ${formData?.name} deleted successfully`);
            })
            .catch(error => {
                console.error('Error deleting company:', error);
            });
    };

    if (formData === null) {
        return <div>Company not found or loading...</div>;
    }

    return (
        <div>
            <div className="topButtonsRow">
                <Link to={`/`}>
                    <h2 className="backButton">Back</h2>
                </Link>
                <button className="deleteButton" onClick={handleDelete}>Delete
                </button>
            </div>

            <form onSubmit={handleSubmit}>
                <label>Name:
                    <input type="text" name="name" value={formData.name} onChange={handleChange} required/>
                    <br/>
                </label>
                <label>Country:
                    <input type="text" name="country" value={formData.country} onChange={handleChange} required/>
                    <br/>
                </label>
                <label>City:
                    <input type="text" name="city" value={formData.city} onChange={handleChange} required/>
                    <br/>
                </label>
                <label>PLZ:
                    <input type="text" name="plz" value={formData.plz} onChange={handleChange} required/>
                    <br/>
                </label>
                <label>Street:
                    <input type="text" name="street" value={formData.street} onChange={handleChange} required/>
                    <br/>
                </label>
                <label>Street Number:
                    <input type="text" name="streetNumber" value={formData.streetNumber} onChange={handleChange}/>
                    <br/>
                </label>
                <label>Phone Number:
                    <input type="text" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange}/>
                    <br/>
                </label>
                <label>Email:
                    <input type="email" name="email" value={formData.email} onChange={handleChange}/>
                    <br/>
                </label>
                <label>Website:
                    <input type="url" name="website" value={formData.website} onChange={handleChange}/>
                    <br/>
                </label>
                <label>Comment:
                    <textarea name="comment" value={formData.comment} onChange={handleChange}/>
                    <br/>
                </label>
                <button className="updateButton" type="submit">Update Company</button>
            </form>
        </div>
    );
}

export default CompanyDetail;
