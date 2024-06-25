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
                navigate('/company');
                alert('Company updated successfully');
            } catch (error) {
                console.error('Error updating company:', error);
            }
        }
    };

    const handleDelete = () => {
        axios.delete(`/api/company/${id}`)
            .then(() => {
                navigate('/company');
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
        <div className="container">
            <div className="topButtonsRow">
                <Link to={`/company`}>
                    <button className="backButton">Back</button>
                </Link>
                <button className="deleteButton" onClick={handleDelete}>Delete</button>
            </div>
            <form onSubmit={handleSubmit} className="companyForm">
                <div className="formGroup">
                    <label htmlFor="name">Name:</label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="formGroup">
                    <label htmlFor="country">Country:</label>
                    <input
                        type="text"
                        id="country"
                        name="country"
                        value={formData.country}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="formGroup">
                    <label htmlFor="city">City:</label>
                    <input
                        type="text"
                        id="city"
                        name="city"
                        value={formData.city}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="formGroup">
                    <label htmlFor="plz">PLZ:</label>
                    <input
                        type="text"
                        id="plz"
                        name="plz"
                        value={formData.plz}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="formGroup">
                    <label htmlFor="street">Street:</label>
                    <input
                        type="text"
                        id="street"
                        name="street"
                        value={formData.street}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="formGroup">
                    <label htmlFor="streetNumber">Street Number:</label>
                    <input
                        type="text"
                        id="streetNumber"
                        name="streetNumber"
                        value={formData.streetNumber}
                        onChange={handleChange}
                    />
                </div>
                <div className="formGroup">
                    <label htmlFor="phoneNumber">Phone Number:</label>
                    <input
                        type="text"
                        id="phoneNumber"
                        name="phoneNumber"
                        value={formData.phoneNumber}
                        onChange={handleChange}
                    />
                </div>
                <div className="formGroup">
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                    />
                </div>
                <div className="formGroup">
                    <label htmlFor="website">Website:</label>
                    <input
                        type="url"
                        id="website"
                        name="website"
                        value={formData.website}
                        onChange={handleChange}
                    />
                </div>
                <div className="formGroup">
                    <label htmlFor="comment">Comment:</label>
                    <textarea
                        id="comment"
                        name="comment"
                        value={formData.comment}
                        onChange={handleChange}
                    ></textarea>
                </div>
                <button className="updateButton" type="submit">Update Company</button>
            </form>
        </div>


    );
}

export default CompanyDetail;
