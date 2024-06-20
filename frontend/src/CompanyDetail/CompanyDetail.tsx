import { useEffect, useState } from 'react';
import {Link, useParams} from 'react-router-dom';
import axios from 'axios';
import {Company} from "../CompanyCard/Company.ts";


function CompanyDetail() {
    const { id } = useParams<{ id: string }>();
    const [company, setCompany] = useState<Company | null>(null);

    useEffect(() => {
        axios.get(`/api/company/${id}`)
            .then(response => {
                setCompany(response.data);
            })
            .catch(() => {
                setCompany(null);
            });
    }, [id]);

    if (company === null) {
        return <div>Company not found or loading...</div>;
    }

    return (
        <div>
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
