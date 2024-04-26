const postulanteButton = document.getElementById("input-postulante");
const empresaButton = document.getElementById("input-empresa");
const postulanteForm = document.getElementById("register-postulante-form");
const empresaForm = document.getElementById("register-empresa-form");
const loginAccept = document.getElementById("log-in-accept");
const registerAccept = document.getElementById("register-accept");



postulanteButton.checked = false;
empresaButton.checked = false;
postulanteForm.innerHTML = ``;
empresaForm.innerHTML = ``;

postulanteButton.addEventListener("click", () => {
    empresaButton.checked = false;
    empresaForm.innerHTML = ``;
    postulanteForm.innerHTML = `<label for="input-date"><b>Fecha de Nacimiento: </b></label>
                                <input type="date" id="input-date"><br>
                                <label for="input-nationality"><b>Nacionalidad: </b></label>
                                <input type="text" id="input-nationality"><br>`;
})

empresaButton.addEventListener("click", () => {
    postulanteButton.checked = false;
    postulanteForm.innerHTML = ``;
    empresaForm.innerHTML = `<label for="input-website"><b>Sitio Web: </b></label>
                            <input type="text" id="input-website"><br>
                            <div class="description-container">
                                <label for="input-description"><b>Descripción: </b></label>
                                <textarea id="input-description"></textarea><br>
                            </div>`;
})

registerAccept.addEventListener("click", () => {
    location.href = "PerfilEmpresa.html"
})

loginAccept.addEventListener("click", () => {
    location.href = "PerfilPostulante.html"
})