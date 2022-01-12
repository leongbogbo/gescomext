
const progress = (value) => {
   document.getElementsByClassName('progress-bar')[0].style.width = `${value}%`;
}

   let step = document.getElementsByClassName('step');
   let prevBtn = document.getElementById('prev-btn');
   let nextBtn = document.getElementById('next-btn');
   let submitBtn = document.getElementById('submit-btn');
   let form = document.getElementsByTagName('form')[0];
   let preloader = document.getElementById('preloader-wrapper');
   let bodyElement = document.querySelector('body');
   let succcessDiv = document.getElementById('success');
 
   //form.onsubmit = () => { return false }

   let current_step = 0;
   var count= $('div.step').length;
   let stepCount =count-1
   step[current_step].classList.add('d-block');
   if(current_step == 0){
      prevBtn.classList.add('d-none');
      submitBtn.classList.add('d-none');
      nextBtn.classList.add('d-inline-block');
   }

	
   nextBtn.addEventListener('click', () => {
	var struc = "";
	if($("#typeStructure").find(":selected").val()==7){
		struc = $("#numIduEntr").val()
	}else{
		struc = $("#departement").find(":selected").val()
	}
	
	if($('#UsageGag').is(':checked') || $('#UsageGag2').is(':checked')) { //on est dans levee de gage
		
		if(typeof $("#typeStructure").find(":selected").val()!="undefined"){
			if(
			   current_step == 1 && $("#typeStructure").find(":selected").val()==""
			|| current_step == 2 && $("#nomEntr").val()=="" || current_step == 2 && struc == ""|| current_step == 2 && $("#comptecontriEntr").val()==""
			|| current_step == 3 && $("#formeJuridique").find(":selected").val()=="" || current_step == 3 && $("#domaineActivite").find(":selected").val()==""
			|| current_step == 3 && $("#ville").find(":selected").val()=="" || current_step == 3 && $("#commune").find(":selected").val()==""
			|| current_step == 4 && $("#telEntr").val()=="" || current_step == 4 && $("#emailEntr").val()==""
			|| current_step == 5 && $("#nomProp").val()=="" || current_step == 5 && $("#prenomsProp").val()==""
			|| current_step == 5 && $("#sexeProp").find(":selected").val()=="" || current_step == 5 && $("#nationalite").find(":selected").val()==""
			|| current_step == 6 && $("#typePieceIdentite").find(":selected").val()=="" || current_step == 6 && $("#numpieceProp").val()==""
			|| current_step == 6 && $("#validitePieceProp").val()=="" || current_step == 6 && $("#telProp").val()=="" || current_step == 6 && $("#emailProp").val()==""	
			|| current_step == 7 && $("#marque").find(":selected").val()=="" || current_step == 7 && $("#genreMarque").find(":selected").val()==""
			|| current_step == 7 && $("#numImmatriculationtGag").val()=="" || current_step == 7 && $("#dateMiseCirculationGag").val()==""
			|| current_step == 8 && $("#numCarteGriseGag").val()=="" || current_step == 8 && $("#dateGag").val()==""
			|| current_step == 8 && $("#numChassisGag").val()=="" || current_step == 8 && $("#TypeTechGag").val()==""
			
				
			){
				return false
			}			
		}else{
			if(
			   current_step == 1 && $("#nomDem").val()=="" || current_step == 1 && $("#prenomsDem").val()==""
			|| current_step == 1 && $("#sexeDem").find(":selected").val()=="" || current_step == 1 && $("#nationalite").find(":selected").val()==""
			|| current_step == 2 && $("#typePieceIdentite").find(":selected").val()=="" || current_step == 2 && $("#numpieceDem").val()==""
			|| current_step == 2 && $("#validitePieceDem").val()=="" || current_step == 2 && $("#telDem").val()=="" || current_step == 2 && $("#emailDem").val()==""
			
			|| current_step == 3 && $("#marque").find(":selected").val()=="" || current_step == 3 && $("#genreMarque").find(":selected").val()==""
			|| current_step == 3 && $("#numImmatriculationtGag").val()=="" || current_step == 3 && $("#dateMiseCirculationGag").val()==""
			|| current_step == 4 && $("#numCarteGriseGag").val()=="" || current_step == 4 && $("#dateGag").val()==""
			|| current_step == 4 && $("#numChassisGag").val()=="" || current_step == 4 && $("#TypeTechGag").val()==""	
			){
				return false
			}
		}
		
			 
		
	 }else{ //on est dans les deux aures
		if($("#typeStructure").find(":selected").val()!=1){
			if(
			   current_step == 0 && $("#typeStructure").find(":selected").val()==""
			|| current_step == 1 && $("#nomEntr").val()=="" || current_step == 1 && struc == ""|| current_step == 1 && $("#comptecontriEntr").val()==""
			|| current_step == 2 && $("#formeJuridique").find(":selected").val()=="" || current_step == 2 && $("#domaineActivite").find(":selected").val()==""
			|| current_step == 2 && $("#ville").find(":selected").val()=="" || current_step == 2 && $("#commune").find(":selected").val()==""
			|| current_step == 3 && $("#telEntr").val()=="" || current_step == 3 && $("#emailEntr").val()==""
			|| current_step == 4 && $("#nomProp").val()=="" || current_step == 4 && $("#prenomsProp").val()==""
			|| current_step == 4 && $("#sexeProp").find(":selected").val()=="" || current_step == 4 && $("#nationalite").find(":selected").val()==""
			|| current_step == 5 && $("#typePieceIdentite").find(":selected").val()=="" || current_step == 5 && $("#numpieceProp").val()==""
			|| current_step == 5 && $("#validitePieceProp").val()=="" || current_step == 5 && $("#telProp").val()=="" || current_step == 5 && $("#emailProp").val()==""	
			|| current_step == 6 && $("#numFactureOcca").val()=="" || current_step == 6 && $("#emetteurOcca").val()==""
			|| current_step == 6 && $("#declarationOcca").val()=="" || current_step == 6 && $("#objetOcca").val()=="" || current_step == 6 && $("#dateEmisOcca").val()==""
			){
				return false
			}
		}
		if($("#typeStructure").find(":selected").val()==1){
			if(
			   current_step == 1 && $("#nomDem").val()=="" || current_step == 1 && $("#prenomsDem").val()==""
			|| current_step == 1 && $("#sexeDem").find(":selected").val()=="" || current_step == 1 && $("#nationalite").find(":selected").val()==""
			|| current_step == 2 && $("#typePieceIdentite").find(":selected").val()=="" || current_step == 2 && $("#numpieceDem").val()==""
			|| current_step == 2 && $("#validitePieceDem").val()=="" || current_step == 2 && $("#telDem").val()=="" || current_step == 2 && $("#emailDem").val()==""
			
			|| current_step == 3 && $("#numFactureOcca").val()=="" || current_step == 3 && $("#emetteurOcca").val()==""
			|| current_step == 3 && $("#declarationOcca").val()=="" || current_step == 3 && $("#objetOcca").val()=="" || current_step == 3 && $("#dateEmisOcca").val()==""	
			){
				return false
			}			
		}
		
						
	}
      current_step++;
      let previous_step = current_step - 1;
      if(( current_step > 0) && (current_step <= stepCount)){
        prevBtn.classList.remove('d-none');
        prevBtn.classList.add('d-inline-block');
        step[current_step].classList.remove('d-none');
        step[current_step].classList.add('d-block');
        step[previous_step].classList.remove('d-block');
        step[previous_step].classList.add('d-none');
        if (current_step == stepCount){
          submitBtn.classList.remove('d-none');
          submitBtn.classList.add('d-inline-block');
          nextBtn.classList.remove('d-inline-block');
          nextBtn.classList.add('d-none');
        }
      } else {
        if(current_step > stepCount){
            form.onsubmit = () => { return true }
        }
      }
    progress((100 / stepCount) * current_step);
    });


   prevBtn.addEventListener('click', () => {
     if(current_step > 0){
        current_step--;
        let previous_step = current_step + 1; 
        prevBtn.classList.add('d-none');
        prevBtn.classList.add('d-inline-block');
        step[current_step].classList.remove('d-none');
        step[current_step].classList.add('d-block')
        step[previous_step].classList.remove('d-block');
        step[previous_step].classList.add('d-none');
        if(current_step < stepCount){
           submitBtn.classList.remove('d-inline-block');
           submitBtn.classList.add('d-none');
           nextBtn.classList.remove('d-none');
           nextBtn.classList.add('d-inline-block');
           prevBtn.classList.remove('d-none');
           prevBtn.classList.add('d-inline-block');
        } 
     }

     if(current_step == 0){
        prevBtn.classList.remove('d-inline-block');
        prevBtn.classList.add('d-none');
     }
    progress((100 / stepCount) * current_step);
   });


submitBtn.addEventListener('click', () => {
    preloader.classList.add('d-block');

    const timer = ms => new Promise(res => setTimeout(res, ms));

    timer(3000)
      .then(() => {
           bodyElement.classList.add('loaded');
      }).then(() =>{
          step[stepCount].classList.remove('d-block');
          step[stepCount].classList.add('d-none');
          prevBtn.classList.remove('d-inline-block');
          prevBtn.classList.add('d-none');
          submitBtn.classList.remove('d-inline-block');
          submitBtn.classList.add('d-none');
          succcessDiv.classList.remove('d-none');
          succcessDiv.classList.add('d-block');
      })
      
      if(current_step == 0 && $("#typeStructure").find(":selected").val()==""){
		$("#next-btn").css("display","none");
	}
      
});


