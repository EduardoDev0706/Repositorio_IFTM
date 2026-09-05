import { Link } from 'react-router'

function Menu() {
    return (  
        <nav>
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
            </ul>

            <ul>
                <li>
                    <Link to="/contatos">Contatos</Link>
                </li>
            </ul>
        </nav>
    );
}

export default Menu;