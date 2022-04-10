(function ($) {
    "use strict";

    // metisMenu 
$("#sidebar_menu").metisMenu();
// metisMenu 
$("#admin_profile_active").metisMenu();
	
$(".open_miniSide").click(function () {
    $(".sidebar").toggleClass("mini_sidebar");
    $(".main_content ").toggleClass("full_main_content");
    $(".footer_part ").toggleClass("full_footer");
  });
$(window).on('scroll', function () {
	var scroll = $(window).scrollTop();
	if (scroll < 400) {
    $('#back-top').fadeOut(500);
	} else {
    $('#back-top').fadeIn(500);
	}
});

// back to top 
$('#back-top a').on("click", function () {
    $('body,html').animate({
      scrollTop: 0
    }, 1000);
    return false;
  });


// PAGE ACTIVE 
$( "#sidebar_menu" ).find( "a" ).removeClass("active");
$( "#sidebar_menu" ).find( "li" ).removeClass("mm-active");
$( "#sidebar_menu" ).find( "li ul" ).removeClass("mm-show");

var current = window.location.pathname
$("#sidebar_menu >li a").filter(function() {

    var link = $(this).attr("href");
    if(link){
        if (current.indexOf(link) != -1) {
            $(this).parents().parents().children('ul.mm-collapse').addClass('mm-show').closest('li').addClass('mm-active');
            $(this).addClass('active');
            return false;
        }
    }
});

// #NOTIFICATION_ 
	// for MENU notification
	$('.bell_notification_clicker').on('click', function () {
		$('.Menu_NOtification_Wrap').toggleClass('active');
	});

	$(document).click(function(event){
        if (!$(event.target).closest(".bell_notification_clicker ,.Menu_NOtification_Wrap").length) {
            $("body").find(".Menu_NOtification_Wrap").removeClass("active");
        }
    });
    	// CHAT_MENU_OPEN 
        $('.CHATBOX_open').on('click', function() {
            $('.CHAT_MESSAGE_POPUPBOX').toggleClass('active');
        });
        $('.MSEESAGE_CHATBOX_CLOSE').on('click', function() {
            $('.CHAT_MESSAGE_POPUPBOX').removeClass('active');
        });
        $(document).click(function(event) {
            if (!$(event.target).closest(".CHAT_MESSAGE_POPUPBOX, .CHATBOX_open").length) {
                $("body").find(".CHAT_MESSAGE_POPUPBOX").removeClass("active");
            }
        });

    	// CHAT_MENU_OPEN 
        $('.serach_button').on('click', function() {
            $('.serach_field-area ').addClass('active');
        });
        
        $(document).click(function(event) {
            if (!$(event.target).closest(".serach_button, .serach_field-area").length) {
                $("body").find(".serach_field-area").removeClass("active");
            }
        });
    //progressbar js
    $(document).ready(function(){
        var proBar = $('#bar1');
        if(proBar.length){
            proBar.barfiller({barColor: '#FFBF43', duration: 2000});
        }
        var proBar = $('#bar2');
        if(proBar.length){
            proBar.barfiller({barColor: '#508FF4', duration: 2100});
        }
        var proBar = $('#bar3');
        if(proBar.length){
            proBar.barfiller({barColor: '#4BE69D', duration: 2200});
        }
        var proBar = $('#bar4');
        if(proBar.length){
            proBar.barfiller({barColor: '#FD3C97', duration: 2200});
        }
        var proBar = $('#bar5');
        if(proBar.length){
            proBar.barfiller({barColor: '#6D81F5', duration: 2200});
        }
        var proBar = $('#bar6');
        if(proBar.length){
            proBar.barfiller({barColor: '#0DC8DE', duration: 2200});
        }
        var proBar = $('#bar7');
        if(proBar.length){
            proBar.barfiller({barColor: '#FFB822', duration: 2200});
        }
        
    });
    
    
    //notification section js
    $(".close_icon").click(function () {
      $(this).parents(".hide_content").slideToggle("0");
    });




    //count up js
    var count= $('.counter');
        if(count.length){
        count.counterUp({
            delay: 100,
            time: 5000
        });
    }


    // data table 

    
    //niceselect select jquery
    // $('.nice_Select').niceSelect();
    // //niceselect select jquery
    // $('.nice_Select2').niceSelect();
    // $('.default_sel').niceSelect();

    // niceSelect 
    var niceSelect = $('.nice_Select');
    if (niceSelect.length) {
        niceSelect.niceSelect();
    };

    var niceSelect = $('.nice_Select2');
    if (niceSelect.length) {
        niceSelect.niceSelect();
    };

    var niceSelect = $('.default_sel');
    if (niceSelect.length) {
        niceSelect.niceSelect();
    };


    // datepicker 
    $(document).ready(function() {
        var date_picker = $('#start_datepicker');
        if(date_picker.length){
            date_picker.datepicker();
        }

        var date_picker = $('#end_datepicker');
        if(date_picker.length){
            date_picker.datepicker();
        }
    });



    //progressbar js
    var delay = 500;
    $(".progress-bar").each(function(i){
        $(this).delay( delay*i ).animate( { width: $(this).attr('aria-valuenow') + '%' }, delay );

        $(this).prop('Counter',0).animate({
            Counter: $(this).text()
        }, {
            duration: delay,
            easing: 'swing',
            step: function (now) {
                $(this).text(Math.ceil(now)+'%');
            }
        });
    });

    //active sidebar
    $('.sidebar_icon').on('click', function(){
        $('.sidebar').toggleClass('active_sidebar');
    });
    $('.sidebar_close_icon i').on('click', function(){
        $('.sidebar').removeClass('active_sidebar');
    });
    
    //active menu
    $('.troggle_icon').on('click', function(){
        $('.setting_navbar_bar').toggleClass('active_menu');
    });

    //active courses option
    // $('.courses_option').on('click', function(){
    //     $(this).parent(".custom_select").toggleClass('active');
    // });

    $('.custom_select').click( function(){
        if ( $(this).hasClass('active') ) {
            $(this).removeClass('active');
        } else {
            $('.custom_select.active').removeClass('active');
            $(this).addClass('active');    
        }
    });
//     $( 'ul.nav li' ).on( 'click', function() {
//         $( this ).parent().find( 'li.active' ).removeClass( 'active' );
//         $( this ).addClass( 'active' );
//   });

    $(document).click(function(event){
        if (!$(event.target).closest(".custom_select").length) {
            $("body").find(".custom_select").removeClass("active");
        }
    });
    //remove sidebar
    $(document).click(function(event){
        if (!$(event.target).closest(".sidebar_icon, .sidebar").length) {
            $("body").find(".sidebar").removeClass("active_sidebar");
        }
    });
    
    // check all
    $("#checkAll").click(function () {
        $('input:checkbox').not(this).prop('checked', this.checked);
    });

    // sumer note
    var summerNote = $('#summernote');
    if(summerNote.length){
        summerNote.summernote({
            placeholder: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
            tabsize: 2,
            height: 305
        });
    }
    // sumer note
    var summerNote = $('.lms_summernote');
    if(summerNote.length){
        summerNote.summernote({
            placeholder: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
            tabsize: 2,
            height: 305
        });
    }
    // sumer note
    var summerNote = $('.lms_summernote');
    if(summerNote.length){
        summerNote.summernote({
            placeholder: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
            tabsize: 2,
            height: 305
        });
    }
    
    
    //custom file
    $('.input-file').each(function() {
        var $input = $(this),
            $label = $input.next('.js-labelFile'),
            labelVal = $label.html();
        
       $input.on('change', function(element) {
          var fileName = '';
          if (element.target.value) fileName = element.target.value.split('\\').pop();
          fileName ? $label.addClass('has-file').find('.js-fileName').html(fileName) : $label.removeClass('has-file').html(labelVal);
       });
    });
    
    //custom file
    $('.input-file2').each(function() {
        var $input = $(this),
            $label = $input.next('.js-labelFile1'),
            labelVal = $label.html();
        
       $input.on('change', function(element) {
          var fileName = '';
          if (element.target.value) fileName = element.target.value.split('\\').pop();
          fileName ? $label.addClass('has-file').find('.js-fileName1').html(fileName) : $label.removeClass('has-file').html(labelVal);
       });
    });

    // meta_keywords 
    var bootstrapTag =  $("#meta_keywords");
    if(bootstrapTag.length){
        bootstrapTag.tagsinput();
    }

//DATA TABLE CONFIGURATION
  if ($('.lms_table_active').length) {
    $('.lms_table_active').DataTable({
        bLengthChange: false,
        "bDestroy": true,
        language: {
            search: "<i class='ti-search'></i>",
            searchPlaceholder: 'Quick Search',
            paginate: {
                next: "<i class='ti-arrow-right'></i>",
                previous: "<i class='ti-arrow-left'></i>"
            }
        },
        columnDefs: [{
            visible: false
        }],
        "order": [[ 0, "desc" ]],
        responsive: true,
        searching: false,
    });
}
  if ($('.lms_table_active2').length) {
    $('.lms_table_active2').DataTable({
        bLengthChange: false,
        "bDestroy": false,
        language: {
            search: "<i class='ti-search'></i>",
            searchPlaceholder: 'Quick Search',
            paginate: {
                next: "<i class='ti-arrow-right'></i>",
                previous: "<i class='ti-arrow-left'></i>"
            }
        },
        columnDefs: [{
            visible: false
        }],
        "order": [[ 0, "desc" ]],
        responsive: true,
        searching: false,
        info: false,
        paging: false
    });
}
//   layout select
  $('.layout_style').click( function(){
    if ( $(this).hasClass('layout_style_selected') ) {
        $(this).removeClass('layout_style_selected');
    } else {
        $('.layout_style.layout_style_selected').removeClass('layout_style_selected');
        $(this).addClass('layout_style_selected');    
    }
});



// switcher menu 
// anly for side switcher menu 
$('.switcher_wrap li.Horizontal').click( function(){
    $('.sidebar').addClass('hide_vertical_menu');
    $('.main_content ').addClass('main_content_padding_hide');
    $('.horizontal_menu').addClass('horizontal_menu_active');
    $('.main_content_iner').addClass('main_content_iner_padding');
    $('.footer_part').addClass('pl-0');
});

$('.switcher_wrap li.vertical').click( function(){
    $('.sidebar').removeClass('hide_vertical_menu');
    $('.main_content ').removeClass('main_content_padding_hide');
    $('.horizontal_menu').removeClass('horizontal_menu_active');
    $('.main_content_iner').removeClass('main_content_iner_padding');
    $('.footer_part').removeClass('pl-0');
});

// switcher_wrap 
// anly for side switcher menu 

$('.switcher_wrap li').click(function(){
    $('li').removeClass("active");
    $(this).addClass("active");
});

$('.custom_lms_choose li').click(function(){
    $('li').removeClass("selected_lang");
    $(this).addClass("selected_lang");
});


$('.spin_icon_clicker').on('click', function(e) {
    $('.switcher_slide_wrapper').toggleClass("swith_show"); //you can list several class names 
    e.preventDefault();
  });

//   color skin 
  $(document).ready(function(){
    $(function () {
        "use strict";
        $(".pCard_add").click(function () {
          $(".pCard_card").toggleClass("pCard_on");
          $(".pCard_add i").toggleClass("fa-minus");
        });
      });
    });
    
    //---------------------------------------GESTION PERSONNEL--------------------------//
    $('#typeStructure').change(function(){
	    var nbre = $(this).val();
	    if(nbre == 8){
	    	$('#numIduEntr').val("").attr('disabled','disabled');
            $("#exoregcomBody,#regcommerceBody,#numIduBody,#ancienselect,#nouvauselect,#typeRCCMEntrBody").hide();
            $("#departementBody").show();		
		}else{
			$("#numIduEntr").removeAttr('disabled');
            $("#exoregcomBody,#regcommerceBody,#numIduBody,#ancienselect,#typeRCCMEntrBody").show();
            $("#departementBody").hide();
		}
	});
	
	$('#exoregcomEntr').change(function(){
	    var nbre = $(this).val();
	    if(nbre == 'non'){
			$("input").removeAttr('disabled');
			$('#typeRCCMEntr').removeAttr('disabled');
		}else{
	    	$('#regcommerceEntr').val("").attr('disabled','disabled');
	    	$('#typeRCCMEntr').val("").attr('disabled','disabled');
	    	$('#regcommerceEntranc').val("").attr('disabled','disabled');
	    	
		}
	});
	$("#nouvauselect").hide();
	$('#typeRCCMEntr').change(function(){
	    var nbre = $(this).val();
	    if(nbre == 'nouveau'){
			$("#nouvauselect").show();
			$("#ancienselect").hide();
		}else{
	    	$("#ancienselect").show();
	    	$("#nouvauselect").hide();
		}
	});
	
	//if(typeof input != 'undefined'){
		 $(":input").inputmask();
		 
		 $("#numIduEntr").inputmask({
			mask: 'CI 9999 9999999 A',
			placeholder: '__-____-_______-_',
			showMaskOnHover: true,
			showMaskOnFocus: false
		});
		
		$("input").inputmask({
			casing:'upper'
		});
		
		 $("#regcommerceEntrNBV").inputmask({
			mask: 'AA-AAA-99-9999-A99-999999',
			placeholder: '__-___-__-____-___-______',
			showMaskOnHover: true,
			showMaskOnFocus: true
		});
		
		$("#contribuableEntr").inputmask({
			mask: '9999999A',
			placeholder: '________',
			showMaskOnHover: true,
			showMaskOnFocus: false
		});
		
		 $(".maskPhone").inputmask({
			mask: '99 99 99 99 99',
			placeholder: '__ __ __ __ __',
			showMaskOnHover: true,
			showMaskOnFocus: true
		});
		$(".maskEmail").inputmask("email");
	//}
	/**************************--------------- VALIDATION DE CODE---------------------****************** */
	
	$("#regcommerceEntr").on("blur", function () {
		var convert=0;
	  	var regCode = $("#regcommerceEntr").val().split("-");
	  	alert(regCode.length);
	  	if(regCode.length <5 || regCode.length >6){
			$("#regcommerceEntr").val("");
			$("#infoRccm").text("Format non conforme");
			alert("Format non conforme1");	
		}else{
			if(regCode.length==6){
				convert = parseInt(regCode[2]);			
				if(isNaN(convert) || $.isNumeric(regCode[2]) == false){
					$("#regcommerceEntr").val("");
					$("#infoRccm").text("Le code juridique doit être un nombre : "+regCode[2]);	
					alert("Format non conforme : "+regCode[2]);	
				}
				if(regCode[0].length != 2 || regCode[1].length != 3 || regCode[2].length != 2 || regCode[3].length != 4 || regCode[4].length != 3){
					$("#regcommerceEntr").val("");
					$("#infoRccm").text("Format non conforme");
					alert("Format non conforme2");	
				}
			}else if(regCode.length==5){
				if(regCode[0].length != 2 || regCode[1].length != 3 || regCode[2].length != 4){
					$("#regcommerceEntranc").val("");
					$("#infoRccm").text("Format non conforme");
					alert("Format non conforme3");	
				}
			}
		}
	});
	
	$("#regcommerceEntranc").on("blur", function () {
	  	var regCode = $("#regcommerceEntranc").val().split("-");
	  	if(regCode.length !=5){
			$("#regcommerceEntranc").val("");
			$("#infoRccm").text("Format non conforme");
			alert("Format non conforme");	
		}else{						
			if(regCode[0].length != 2 || regCode[1].length != 3 || regCode[2].length != 4){
				$("#regcommerceEntranc").val("");
				$("#infoRccm").text("Format non conforme");
				alert("Format non conforme");	
			}
		}
	});
	
	$("#contribuableEntr").on("blur", function () {
		var convert=0;
	  	var regCode = $("#contribuableEntr").val();
	  	if(regCode.length !=8){
			$("#contribuableEntr").val("");
			$("#infoContri").text("Format non conforme");
			alert("Format non conforme");	
		}else if(regCode.substr(7, 8) === "_" || $.isNumeric(regCode.substr(7, 8)) == true){
				$("#contribuableEntr").val("");
				$("#infoContri").text("Le dernier caractere n'est pas correcte : "+regCode.substr(7, 8));	
				alert("Le dernier caractere n'est pas correcte : "+regCode.substr(7, 8));
		}else{
			convert = parseInt(regCode.substr(0, 7));			
			if(isNaN(convert) || $.isNumeric(regCode.substr(0, 7)) == false){
				$("#contribuableEntr").val("");
				$("#infoContri").text("Les 7 premiers caracteres doivent être des nombres : "+regCode.substr(0, 7));	
				alert("Les 7 premiers caracteres doivent être des nombres : "+regCode.substr(0, 7));	
			}
		}
	});
	
	
	$("#ville").on('change', function(e) {
		$("#commune").empty();
		listeCommuneByVille()    
  	});
  	
  	//$(".dispPasse").css("display", "none");
  	
  	$("#role_id").on('change', function(e) {
		$("#importBody").empty();
		$("#occaBody").empty();
		$("#gageBody").empty();
		$("#paramBody").empty();
		$("#statBody").empty();
		listeActionByRole();
  	});
  	
  	$("#site_id").on('change', function(e) {
		$("#userBody").empty();
		listeUserBySite();
  	});
  	
  	$("#nomEntr").on('keyup', function(e) {
		verificationRaisonSociale()    
  	});
  	
  	$("#regcommerceEntr").on('keyup', function(e) {
		verificationRCCM()    
  	});
  	
  	$("#contribuableEntr").on('keyup', function(e) {
		verificationContribuable()    
  	});
  	
  	$("#numIduEntr").on('keyup', function(e) {
		verificationnumIdu()    
  	});
   	
  	/*$("#codeVerif").on('submit', function(e) {
		var codeImport = $("#codeVerif").val();
		if(codeImport.length == 9){
			return false;
		}if(codeImport.length == 10){
			
		}
		
		
  	});*/
	
}(jQuery));

/*********==========================--------------------FONCTION------------------=================================*/
function listeCommuneByVille(){
	villeId = $("#ville").val();
	urlString = "../api/"+villeId;
	$.ajax({metod: "GET", url: urlString})
		.done(function(responseJson){
			designeCommune = $("#commune");
				$("<option>").val('').text('CHOIX').appendTo(designeCommune);
			$.each(responseJson, function(index, commune){				
				$("<option>").val(commune.idCommune).text(commune.nomCommune).appendTo(designeCommune);
			});
		})
		.fail(function(){
			console.log("Erreur pendant le chargement");
		})
		.always(function(){
			
		});
}

function listeActionByRole(){
	roleId = $("#role_id").val();
	urlString = "../api/profile/action/"+roleId;
	urlAction = "../api/profile/listeAction";
	//LISTE DES ACTIONS PAR ROLE
	$.ajax({metod: "GET", url: urlString})
		.done(function(responseJson){
			designeElmt = $("#occaBody");
			//LISTE DES ACTIONS
			$.ajax({metod: "GET", url: urlAction})
				.done(function(responsesJson){
					//designeElmt = $("#occaBody");
					var cop;					
					$.each(responsesJson, function(index, actionListe){
						var reponse="non";
						$.each(responseJson, function(index, actionListes){							
							if(actionListe.lienActPro == actionListes.lienActPro){
								reponse="oui";
							}
						});
						cop = actionListe.lienActPro.split("/");
						if(reponse=="oui"){
							if(cop[0]=='CodeImportExport'){
								$("#importBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input checked type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}else if(cop[0]=='CodeOccasionnel'){
								$("#occaBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input checked type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}else if(cop[0]=='LeveeDeGage'){
								$("#gageBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input checked type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}else if(cop[0]=='parametre'){
								$("#paramBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input checked type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}else if(cop[0]=='administration'){
								$("#adminBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input checked type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}else if(cop[0]=='Statistique'){
								$("#statBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input checked type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}
						}else if(reponse=="non"){
							if(cop[0]=='CodeImportExport'){
								$("#importBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input  type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}else if(cop[0]=='CodeOccasionnel'){
								$("#occaBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input  type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}else if(cop[0]=='LeveeDeGage'){
								$("#gageBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input  type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}else if(cop[0]=='parametre'){
								$("#paramBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input  type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}else if(cop[0]=='administration'){
								$("#adminBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input  type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}else if(cop[0]=='Statistique'){
								$("#statBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input  type='checkbox' class='form-check-input' name='actionListe[]' value='"+actionListe.idActPro+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.titreActPro+"</label></div></div>");
							}
						}
					});
				});
			
		})
		.fail(function(){
			console.log("Erreur pendant le chargement");
		})
		.always(function(){
			
		});
}

function listeUserBySite(){
	elmtId = $("#site_id").val();
	urlString = "../api/parametre/site/"+elmtId;
	urlUser = "../api/parametre/listeUser";
	//LISTE DES ACTIONS PAR ROLE
	$.ajax({metod: "GET", url: urlString})
		.done(function(responseJson){
			designeElmt = $("#userBody");
			//LISTE DES USER
			$.ajax({metod: "GET", url: urlUser})
				.done(function(responsesJson){
					$.each(responsesJson, function(index, actionListe){
						var reponse="non";
						console.log(actionListe);
						$.each(responseJson, function(index, actionListes){							
							if(actionListe.user_id == actionListes.user_id){
								reponse="oui";
							}
						});
						if(reponse=="oui"){
								$("#userBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input checked type='checkbox' class='form-check-input' name='user[]' value='"+actionListe.user_id+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.nomUser+" "+actionListe.prenomsUser+"</label></div></div>");
						}else if(reponse=="non" && actionListe.site == null){
								$("#userBody")
								.append("<div class='col-md-6'><div class='form-check mb-2'><input  type='checkbox' class='form-check-input' name='user[]' value='"+actionListe.user_id+"' id='exampleCheck'><label class='form-check-label' >"+actionListe.nomUser+" "+actionListe.prenomsUser+"</label></div></div>");
						}
					});
				});
			
		})
		.fail(function(){
			console.log("Erreur pendant le chargement");
		})
		.always(function(){
			
		});
}

function verificationRaisonSociale(){
	valeur = $("#nomEntr").val();
	urlString = "../api/raisonSociale/"+valeur;
	
	$.ajax({metod: "GET", url: urlString})
		.done(function(responseJson){
			designechp = $("#infoRaison");
			//$.each(responseJson, function(index, elmt){
				if(typeof responseJson.nomEntr != "undefined" && responseJson.nomEntr !=""){
					$("#nomEntr").val("");
					$("#infoRaison").text("element  présent dans la base");				
				}else{
					$("#infoRaison").text(" ");
				}			
			//});
		})
		.fail(function(){
			console.log("Erreur pendant le chargement");
		})
		.always(function(){
		});
}

function verificationRCCM(){
	valeur = $("#regcommerceEntr").val();
	urlString = "../api/regicommerce/"+valeur;
	
	$.ajax({metod: "GET", url: urlString})
		.done(function(responseJson){
			designechp = $("#infoRccm");
			//$.each(responseJson, function(index, elmt){
				if(typeof responseJson.nomEntr != "undefined" && responseJson.nomEntr !=""){
					$("#regcommerceEntr").val("");
					$("#infoRccm").text("element  présent dans la base");				
				}else{
					$("#infoRccm").text(" ");
				}			
			//});
		})
		.fail(function(){
			console.log("Erreur pendant le chargement");
		})
		.always(function(){
		});
}

function verificationContribuable(){
	valeur = $("#contribuableEntr").val();
	urlString = "../api/contribuable/"+valeur;
	
	$.ajax({metod: "GET", url: urlString})
		.done(function(responseJson){
			designechp = $("#infoContri");
			//$.each(responseJson, function(index, elmt){
				if(typeof responseJson.nomEntr != "undefined" && responseJson.nomEntr !=""){
					$("#contribuableEntr").val("");
					$("#infoContri").text("element  présent dans la base");				
				}else{
					$("#infoContri").text(" ");
				}			
			//});
		})
		.fail(function(){
			console.log("Erreur pendant le chargement");
		})
		.always(function(){
		});
}

function verificationnumIdu(){
	valeur = $("#numIduEntr").val();
	urlString = "../api/numidu/"+valeur;
	
	$.ajax({metod: "GET", url: urlString})
		.done(function(responseJson){
			designechp = $("#infoIdu");
			//$.each(responseJson, function(index, elmt){
				if(typeof responseJson.nomEntr != "undefined" && responseJson.nomEntr !=""){
					$("#numIduEntr").val("");
					$("#infoIdu").text("element  présent dans la base");				
				}else{
					$("#infoIdu").text(" ");
				}			
			//});
		})
		.fail(function(){
			console.log("Erreur pendant le chargement");
		})
		.always(function(){
		});
}

