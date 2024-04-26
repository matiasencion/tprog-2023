const postulationsButton = document.getElementById("postulations-button");
const infoContainer = document.getElementById("info-container");
const profileButton = document.getElementById("profile-button");
const editButton = document.getElementById("edit-button");
const nick = document.getElementById("header-nickname");
const profileNickname = document.getElementById("profile-nickname");
const date = document.getElementById("last-date");

function editButtonFunction() {
    if (nick.innerText != profileNickname.innerText) {
        editButton.style.visibility = "hidden";
    } else {
        editButton.style.visibility = "visible";
    }
}

function postulationsButtonFunction() {
    if (nick.innerText != profileNickname.innerText) {
        postulationsButton.style.visibility = "hidden";
    } else {
        postulationsButton.style.visibility = "visible";
    }
}

postulationsButton.style.visibility = "hidden";
postulationsButtonFunction();
editButton.style.visibility = "hidden";
editButtonFunction();

var dt = new Date();

date.innerHTML = dt.toLocaleString();

editButton.addEventListener("click", () => {
    location.href = "ModificarPostulante.html"
});


profileButton.addEventListener("click", () => {
    editButtonFunction();
    infoContainer.innerHTML = `<ul>
                                    <li><p><b>Nickname: </b><span id="profile-nickname">lgarcia</span></p></li>
                                    <li><p><b>Nombre: </b>Lucía</p></li>
                                    <li><p><b>Apellido: </b>García</p></li>
                                    <li><p><b>Email: </b>lgarcia85@gmail.com</p></li>
                                    <li><p><b>Fecha de Nacimiento: </b>15/03/1985</p></li>
                                    <li><p><b>Nacionalidad: </b>Uruguaya</p></li>
                                </ul>`;
})

postulationsButton.addEventListener("click", () => {
    editButton.style.visibility = "hidden";
    infoContainer.innerHTML = `<ul>
                                    <li><a href="infoPostulacionFrontend.html"><div class="list-item">
                                    <div class="list-info">
                                        <img src="assets/oferta-frontend.png"/>
                                        <div class="list-block">
                                            <h2>Desarrollador Frontend</h2>
                                            <ul>
                                                <li><b>Fecha de Postulación: </b>16/08/23</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div></a></li>
                                </ul>`;
}) 

const headerProfile = document.getElementById("header-to-profile");

headerProfile.addEventListener("click", () => {
    location.href = "PerfilPostulante.html"
});