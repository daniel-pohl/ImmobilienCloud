import {Navigate, Outlet} from "react-router-dom";

type ProtectedRouteProps = {
    user: string | undefined;
}

function ProtectedRoute(props: ProtectedRouteProps) {

    const isAuthenticated = props.user != undefined

    return (
        isAuthenticated ? <Outlet/> : <Navigate to={"/login"}/>
    )
}

export default ProtectedRoute;