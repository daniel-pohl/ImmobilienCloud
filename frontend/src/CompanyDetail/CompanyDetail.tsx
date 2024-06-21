import {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import axios from 'axios';
import './CompanyDetail.css';
import {Company} from "../CompanyCard/Company.ts";



function CompanyDetail() {
    const {id} = useParams<{ id: string }>();
    const [company, setCompany] = useState<Company | null>(null);


    const navigate = useNavigate()

    useEffect(() => {
        axios.get(`/api/company/${id}`)
            .then(response => {
                setCompany(response.data);
            })
            .catch(() => {
                setCompany(null);
            });
    }, [id]);

    const handleDelete = () => {
        axios.delete(`/api/company/${id}`)
            .then(() => {
                navigate(-1);
                alert("Company "+  company?.name + "deleted successfully");
            })
            .catch(error => {
                console.error('Error deleting company:', error);
            });
    };
    if (company === null) {
        return <div>Company not found or loading...</div>;
    }

    return (
        <div>

            <br/>
            <button className={"deleteButton"} onClick={handleDelete}>Delete</button>
            <br/>
            <Link to={`/`}>
                <h2>Back</h2>
            </Link>
            <h1>Detailansicht Firma</h1>
            <h2>{company.name}</h2>
            <p>{company.city}, {company.plz} </p>
            <p>{company.country}</p>
            <p>{company.street} {company.streetNumber}</p>
            <p>{company.phoneNumber}</p>
            <p>{company.email}</p>
            <p>{company.website}</p>
            <p>{company.comment}</p>
        </div>
    );
}

export default CompanyDetail;
