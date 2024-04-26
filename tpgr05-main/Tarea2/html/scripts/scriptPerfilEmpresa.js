const offersButton = document.getElementById("offers-button");
const infoContainer = document.getElementById("info-container");
const profileButton = document.getElementById("profile-button");
const packagesButton = document.getElementById("packages-button");
const editButton = document.getElementById("edit-button");
const nick = document.getElementById("header-nickname");
const profileNickname = document.getElementById("profile-nickname");
const date = document.getElementById("last-date");

function editButtonFunction() {
    if (nick.innerText != profileNickname.innerHTML) {
        editButton.style.visibility = "hidden";
    } else {
        editButton.style.visibility = "visible";
    }
}

function packagesButtonFunction() {
    if (nick.innerText != profileNickname.innerHTML) {
        packagesButton.style.visibility = "hidden";
    } else {
        packagesButton.style.visibility = "visible";
    }
}

packagesButton.style.visibility = "hidden";
packagesButtonFunction();
editButton.style.visibility = "hidden";
editButtonFunction();

var dt = new Date();

date.innerHTML = dt.toLocaleString();


editButton.addEventListener("click", () => {
    location.href = "ModificarEmpresa.html"
});

profileButton.addEventListener("click", () => {
    editButtonFunction();
    infoContainer.innerHTML = `<ul>
                                    <li><p><b>Nickname: </b><span id="porfile-nickname">EcoTech</span></p></li>
                                    <li><p><b>Nombre: </b>Sophia</p>
                                    <li><p><b>Apellido: </b>Johnson</p>
                                    <li><p><b>Email: </b>info@EcoTech.com</p></li>
                                    <li><p><b>Sitio Web: </b>http://www.EcoTechInnova.com.uy</p>
                                    <li><p><b>Descripción: </b>EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. 
                                        Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafíos 
                                        ambientales más apremiantes de nuestro tiempo. Desde sistemas de energía renovable y dispositivos de 
                                        monitorización ambiental hasta soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar 
                                        herramientas que permitan a las empresas y comunidades adoptar prácticas más ecológicas sin comprometer la eficiencia. 
                                        Creemos en la convergencia armoniosa entre la tecnología y la naturaleza, y trabajamos incansablemente para impulsar 
                                        un futuro más limpio y sostenible.</p>
                                </ul>`;
})

offersButton.addEventListener("click", () => {
    editButton.style.visibility = "hidden";
    infoContainer.innerHTML = `<ul>
                                    <li>
                                    <a href="infoOfertaFrontendEmpresa.html">
                                    <div class="list-item">
                                        <div class="list-info">
                                            <img src="assets/oferta-frontend.png"/>
                                            <div class="list-block">
                                                <h2>Desarrollador Frontend</h2>
                                                <ul>
                                                    <li><b>Estado: </b>Aprovada</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div></a>
                                </li>
                                <li>
                                    <a href="infoOfertaFrontendEmpresa.html">
                                    <div class="list-item">
                                        <div class="list-info">
                                            <img src="assets/oferta-analista-de-marketing.jpeg"/>
                                            <div class="list-block">
                                                <h2>A. de Marketing Digital</h2>
                                                <ul>
                                                    <li><b>Estado: </b>Aprovada</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div></a>
                                </li>
                                </ul>`;
})

packagesButton.addEventListener("click", () => {
    editButton.style.visibility = "hidden";
    infoContainer.innerHTML = `<ul>
                                    <li>
                                    <a href="PaqueteBasicoEmpresa.html">
                                    <div class="list-item">
                                        <div class="list-info">
                                            <img src="assets/paquete-basico.jpg"/>
                                            <div class="list-block">
                                                <h2>Básico</h2>
                                                <ul>
                                                    <li><b>Fecha de Compra: </b>17/8/2023</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div></a>
                                </li>
                                <li>
                                    <a href="PaqueteBasicoEmpresa.html">
                                    <div class="list-item">
                                        <div class="list-info">
                                            <img src="assets/paquete-destacado.avif"/>
                                            <div class="list-block">
                                                <h2>Destacado</h2>
                                                <ul>
                                                    <li><b>Fecha de Compra: </b>20/8/2023</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div></a>
                                </li>
                                </ul>`;
})

const headerProfile = document.getElementById("header-to-profile");

headerProfile.addEventListener("click", () => {
    location.href = "PerfilEmpresa.html"
});