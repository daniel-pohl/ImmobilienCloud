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
            <h1>{company.name}</h1>
            <p>{company.street} {company.streetNumber}, {company.plz} {company.city}, {company.country}</p>
            <p>{company.phoneNumber}</p>
            <p>{company.email}</p>
            <p>{company.website}</p>
            <p>{company.comment}</p>
        </div>
    );
}

export default CompanyDetail;
