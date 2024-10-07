function bodyLoaded(){
    loadDepartments();
}

function loadDepartments() {
  // Fetch-ovanje podataka sa zadate adrese
  fetch('https://collectionapi.metmuseum.org/public/collection/v1/departments')
    .then(response => response.json())
    .then(json_resp => {
      let dropdownMenu = document.getElementById('dropdown');
      
      // Očisti postojeće stavke ako ih ima
      dropdownMenu.innerHTML = '';

      // Prolaz kroz svaki department u json odgovoru
      json_resp.departments.forEach(department => {
        // Generisanje HTML-a za svaki department
        let department_html = `<li><a class="dropdown-item" href="#">${department.displayName}</a></li>`;
        
        // Dodavanje generisanog HTML-a u dropdown meni
        dropdownMenu.innerHTML += department_html;
      });
    })
    .catch(error => {
      console.error("Došlo je do greške:", error);
    });
}

  function loadImages(){

  }

  function loadExhibitData(){

  }
