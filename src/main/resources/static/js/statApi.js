(function ($) {
    "use strict";

	$("#statForm").on('submit', function(e) {
		$("#tbodys").empty();
		statEntrprise() 
		return false;   
  	});
  	
	$("#statOp").on('submit', function(e) {
		$("#tbodys").empty();
		statOperation() 
		return false;   
  	});
	$(".statDonneesExtraction").on('click', function(e) {
		var texts = $(this).text();		
		statDonneesExtraction(texts) 
  	});
	$(".statDonneesExtractionOp").on('click', function(e) {
		var texts = $(this).text();		
		statDonneesExtractionOp(texts)
  	});

	
}(jQuery));

/*********==========================--------------------FONCTION------------------=================================*/
function statDonneesExtraction(texts){
	formeJId = $("#formeJuridique").val();
	domActId = $("#domaineActivite").val();
	ville = $("#ville").val();
	commune = $("#commune").val();
	nationalite = $("#nationalite").val();
	inputDate = $("#inputDate").val();
	outPutDate = $("#outPutDate").val();
	
	$("#idJuridiques").val(formeJId);
	$("#iddomaineActivite").val(domActId);
	$("#idville").val(ville);
	$("#idcommune").val(commune);
	$("#idnationalite").val(nationalite);
	$("#inputDates").val(inputDate);
	$("#outputDates").val(outPutDate);
	$("#format").text(texts);
}
function statDonneesExtractionOp(texts){
	rubrique = $("#rubrique").val();
	typeOp = $("#typeOp").val();
	siteid = $("#siteid").val();
	fmjuryid = $("#fmjuryid").val();
	domActid = $("#domActid").val();
	roleid = $("#roleid").val();
	userid = $("#userid").val();
	inputDate = $("#inputDate").val();
	outPutDate = $("#outPutDate").val();
	
	$("#rubriques").val(rubrique);
	$("#typeOps").val(typeOp);
	$("#siteids").val(siteid);
	$("#fmjuryids").val(fmjuryid);
	$("#domActids").val(domActid);
	$("#roleids").val(roleid);
	$("#userids").val(userid);
	$("#inputDates").val(inputDate);
	$("#outputDates").val(outPutDate);
	$("#format").text(texts);
}

function statOperation(){
	rubrique = $("#rubrique").val();
	typeOp = $("#typeOp").val();
	siteid = $("#siteid").val();
	fmjuryid = $("#fmjuryid").val();
	domActid = $("#domActid").val();
	roleid = $("#roleid").val();
	userid = $("#userid").val();
	inputDate = $("#inputDate").val();
	outPutDate = $("#outPutDate").val();
	//urlString = "../api/statistique/entreprise/"+formeJId+"/"+domActId;
	urlApi = "../api/statistique/operations/stat";
	listeElmt = "../api/statistique/operations/liste";
	var Tentrprise=0;
	var TGentreprise=0;
	var diffTT = 0;
	var percent = 0;
	var demandeurs="";
	var sigles="";
	//var sommeTotale=0
	$.ajax({metod: "GET", url: listeElmt})
		.done(function(responseJson){
			designeElmt = $("#tbodys");
			var marker = { "rubrique": rubrique, "typeOp": typeOp, "siteid":siteid,
							"fmjuryid": fmjuryid, "domActid": domActid, "roleid":roleid,
							"userid":userid, "inputDate":inputDate,"outPutDate":outPutDate}
		$.ajax({
					metod: "POST",
					url: urlApi,
					data: marker,
			    	contentType: "application/json, charset=utf-8"
		    	})
			.done(function(responsesJson){
				Tentrprise = responsesJson.length;
				TGentreprise = responseJson.length;
				diffTT = TGentreprise-Tentrprise;
				percent = Tentrprise*100/TGentreprise;
				graphCalcul(Tentrprise,diffTT);
				$.each(responsesJson, function(index, listeResult){
					if(listeResult.codeImportation.statutDemandeurCodeImp=='oui'){
						demandeurs = listeResult.codeImportation.entreprise.nomEntr;
						sigles = listeResult.codeImportation.entreprise.sigleEntr
					}else{
						demandeurs = listeResult.codeImportation.demandeur.nomDem+" "+listeResult.codeImportation.demandeur.prenomsDem;
					}
					//sommeTotale = sommeTotale + parseInt(listeResult.montantOp);
					$("#tbodys").append("<tr role=\"row\" class=\"odd\">"+
                        "<td tabindex=\"0\" class=\"sorting_1\">"+
                            "<div class=\"customer d-flex align-items-center\">"+
                                "<div class=\"ml_18\">"+
                                    "<h3 class=\"f_s_14 f_w_600 mb-0\" >"+demandeurs+"</h3>"+
                                    "<span class=\"f_s_12 f_w_700 text_color_8\" >"+sigles+"</span>"+
                                "</div>"+
                            "</div>"+
                        "</td>"+
                        "<td>"+
                            "<div>"+
                                "<h3 class=\"f_s_12 f_w_500 mb-0\" >"+listeResult.typeCodeOp+"</h3>"+
                                "<span class=\"f_s_12 f_w_700 color_text_3\"></span>"+
                            "</div>"+
                        "</td>"+
                        "<td>"+
                            "<div>"+
                                "<h3 class=\"f_s_12 f_w_500 mb-0\">"+listeResult.typeOp+"</h3>"+
                                "<span class=\"f_s_12 f_w_500 color_text_3\"></span>"+
                            "</div>"+
                        "</td>"+
                        "<td>"+
                            "<div>"+
                                "<h3 class=\"f_s_12 f_w_500 mb-0\" >"+listeResult.user.site.nomSite+"</h3>"+
                                "<span class=\"f_s_12 f_w_500 color_text_3\"></span>"+
                            "</div>"+
                        "</td>"+
                    "</tr>")
				});
				if(Tentrprise>=0){
					$("#totalEntr").text(Tentrprise+"/"+TGentreprise+" opération(s)");					
					$("#pourcentage").text(percent.toFixed(2)+"%");					
				}
			});
		})
		.fail(function(){
			console.log("Erreur pendant le chargement");
		})
		.always(function(){
			
		});
}

function statEntrprise(){
	formeJId = $("#formeJuridique").val();
	domActId = $("#domaineActivite").val();
	ville = $("#ville").val();
	commune = $("#commune").val();
	nationalite = $("#nationalite").val();
	inputDate = $("#inputDate").val();
	outPutDate = $("#outPutDate").val();
	urlApi = "../api/statistique/entreprise/stat";
	listeEntr = "../api/statistique/entreprise/liste";
	var Tentrprise=0;
	var TGentreprise=0;
	var diffTT = 0;
	var percent = 0;
	$.ajax({metod: "GET", url: listeEntr})
		.done(function(responseJson){
			designeElmt = $("#tbodys");
			var marker = {idForm: formeJId, domAct : domActId,ville : ville,commune : commune, nationalite : nationalite,inputDate:inputDate,outPutDate:outPutDate}
		$.ajax({
					metod: "POST",
					url: urlApi,
					data: marker,
			    	contentType: "application/json, charset=utf-8"
		    	})
			.done(function(responsesJson){
				Tentrprise = responsesJson.length;
				TGentreprise = responseJson.length;
				diffTT = TGentreprise-Tentrprise;
				percent = Tentrprise*100/TGentreprise;
				graphCalcul(Tentrprise,diffTT);
				$.each(responsesJson, function(index, listeEntrs){
					$("#tbodys").append("<tr role=\"row\" class=\"odd\">"+
                        "<td tabindex=\"0\" class=\"sorting_1\">"+
                            "<div class=\"customer d-flex align-items-center\">"+
                                "<div class=\"ml_18\">"+
                                    "<h3 class=\"f_s_14 f_w_600 mb-0\" >"+listeEntrs.nomEntr+"</h3>"+
                                    "<span class=\"f_s_12 f_w_700 text_color_8\" >"+listeEntrs.sigleEntr+"</span>"+
                                "</div>"+
                            "</div>"+
                        "</td>"+
                        "<td>"+
                            "<div>"+
                                "<h3 class=\"f_s_12 f_w_500 mb-0\" >"+listeEntrs.regcommerceEntr+"</h3>"+
                                "<span class=\"f_s_12 f_w_700 color_text_3\"></span>"+
                            "</div>"+
                        "</td>"+
                        "<td>"+
                            "<div>"+
                                "<h3 class=\"f_s_12 f_w_500 mb-0\">"+listeEntrs.contribuableEntr+"</h3>"+
                                "<span class=\"f_s_12 f_w_500 color_text_3\"></span>"+
                            "</div>"+
                        "</td>"+
                        "<td>"+
                            "<div>"+
                                "<h3 class=\"f_s_12 f_w_500 mb-0\" >"+listeEntrs.telEntr+"</h3>"+
                                "<span class=\"f_s_12 f_w_500 color_text_3\"></span>"+
                            "</div>"+
                        "</td>"+
                    "</tr>")
				});
				if(Tentrprise>=0){
					$("#totalEntr").text(Tentrprise+"/"+TGentreprise+" Entreprise(s)");
					$("#pourcentage").text(percent.toFixed(2)+"%");					
				}
			});
		})
		.fail(function(){
			console.log("Erreur pendant le chargement");
		})
		.always(function(){
			
		});
}


function graphCalcul(Telmt,Tgeneral){
	//var o = window.AdminoxAdmin || {};
    if ($("#platform_type_dates_donut").length) {
	
        echarts.init(document.getElementById("platform_type_dates_donut")).setOption({
            timeline: {
                show: !1,
                data: ["06-16", "05-16", "04-16"],
                label: {
                    formatter: function (e) {
                        return e ? e.slice(0, 3) : null;
                    },
                },
                x: 10,
                y: null,
                x2: 10,
                y2: 0,
                width: 10,
                height: 50,
                backgroundColor: "rgba(0,0,0)",
                borderColor: "#eaeaea",
                borderWidth: 0,
                padding: 5,
                controlPosition: "left",
                autoPlay: !1,
                loop: !1,
                playInterval: 2e3,
                lineStyle: { width: 1, color: "#000", type: "" },
            },
            options: [
                {
                    color: [ "#8950FC", "#FFCA60",  "#E2FFF6"],
                    title: { text: "", subtext: "" },
                    //tooltip: { trigger: "item", formatter: "{a} <br/>{b} : {c} ({d}%)" },
                    legend: { show: !0, x: "left", orient: "vertical", padding: 0, data: ["La selection", "Autres"] },
                    toolbox: {
                        show: !1,
                        color: ["#bdbdbd", "#bdbdbd", "#bdbdbd"],
                        feature: {
                            mark: { show: !1 },
                            dataView: { show: !1, readOnly: !0 },
                            magicType: { show: !0, type: ["pie", "funnel"], option: { funnel: { x: "10%", width: "80%", funnelAlign: "center", max: 50 }, pie: { roseType: "none" } } },
                            restore: { show: !1 },
                            saveAsImage: { show: 0 },
                        },
                    },
                    series: [
                        {
                            name: "06-16",
                            type: "pie",
                            radius: [70, "45%"],
                            roseType: "none",
                            center: ["50%", "45%"],
                            width: "100%",
                            itemStyle: { normal: { label: { show: !1 }, labelLine: { show: !1 } }, emphasis: { label: { show: !1 }, labelLine: { show: !1 } } },
                            /*label: {
						        formatter: '{d}%',
						        position: 'inside',
						        fontSize: 18,
						        color: "white",
						      },*/
                            data: [
                                { value: Telmt, name: "La selection" },
                                { value: Tgeneral, name: "Autres" }
                            ],
                        },
                    ],
                }
            ],
        });
    }
    
}

