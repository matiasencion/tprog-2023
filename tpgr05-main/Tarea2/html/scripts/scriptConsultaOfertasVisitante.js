const infoContainer = document.getElementById("info-container");
const selectEmpresa = document.getElementById("select-empresa");

selectEmpresa.value ="";
infoContainer.innerHTML = ``;

selectEmpresa.addEventListener("change", () => {
    if (selectEmpresa.value == "EcoTech") {
        infoContainer.innerHTML = ` <div class="list-item">
                                    <div class="list-info">
                                        <img src="assets/oferta-frontend.png"/>
                                        <div class="list-block">
                                            <h2>Desarrollador Frontend </h2>
                                        </div>
                                    </div>
                                    <div class="list-buy">
                                        <a href="infoOfertaFrontendEmpresa.html"><button class="button-view" id="view-basico">Ver</button></a>
                                    </div>
                                </div>
                                <div class="list-item">
                                    <div class="list-info">
                                        <img src="assets/oferta-analista-de-marketing.jpeg"/>
                                        <div class="list-block">
                                            <h2>A. de Marketing Digital </h2>
                                        </div>
                                    </div>
                                    <div class="list-buy">
                                        <a href="infoOfertaFrontendEmpresa.html"><button class="button-view" id="view-basico">Ver</button></a>
                                    </div>
                                </div>`;
    } else {
        infoContainer.innerHTML = ``;
    }
})
/*hola*/ 