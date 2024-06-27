import './Sidebar.css';
import {Link} from "react-router-dom";

function Sidebar() {
    return (
        <div className="sidebar">
            <ul>
                <li className="sidebar-list-item">
                    <Link to="/company">Firmen</Link>
                </li>
                <li className="sidebar-list-item">
                    <Link to="/contact">Kontakte</Link>
                </li>
                <li className="sidebar-list-item">
                    <Link to="/">Objekte</Link>
                </li>
                <li className="sidebar-list-item">
                    <Link to="/">Flächen</Link>
                </li>
            </ul>
        </div>
    );
}

export default Sidebar;
