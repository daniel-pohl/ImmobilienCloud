import Sidebar from "../Sidebar/Sidebar.tsx";
import Header from "../Header/Header.tsx";


function MainPage() {
    return (
        <div className="page-container">
            <Header/>
            <div className="sidebar-container">
                <Sidebar/>
                <div className="div-list">
                    <li>
                        hier eine Suchleiste
                    </li>
                    <li>
                        Firmen
                    </li>
                    <li>
                        Firmen
                    </li>
                </div>
            </div>
        </div>
    );
}

export default MainPage;