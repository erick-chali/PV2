(function ($, window, document) {
	  
    
    $(function () {
    	var device = navigator.userAgent

    	if (device.match(/Iphone/i)|| device.match(/Ipod/i)|| device.match(/Android/i)|| device.match(/J2ME/i)|| device.match(/BlackBerry/i)|| device.match(/iPhone|iPad|iPod/i)|| device.match(/Opera Mini/i)|| device.match(/IEMobile/i)|| device.match(/Mobile/i)|| device.match(/Windows Phone/i)|| device.match(/windows mobile/i)|| device.match(/windows ce/i)|| device.match(/webOS/i)|| device.match(/palm/i)|| device.match(/bada/i)|| device.match(/series60/i)|| device.match(/nokia/i)|| device.match(/symbian/i)|| device.match(/HTC/i))
    	{
    	window.location = "movil.pv.jsp";

    	}
    	else
    	{
    		console.log('Dispositivo Normal');
    	}
	   var codigoP, descripcion, medida, cantidad, disponible, precioU, porDesc, descuento, importe, envio, dm, observ;
	   var subTotal = 0;
	   var Total;
	   var temporal;
	   var autorizado = 1;
	   $('#fechaEntrega').mask('99/99/9999',{placeholder:"dd/mm/yyyy"});
	   
//	   $('#indice').hide();
	   $('.datosOcultos').hide();
	   $('.kit').hide();
	   $(document).keydown(function (e){
		   if(e.keyCode==114){
               e.preventDefault();
			   $('#buscarDocumentos').modal('toggle');
               $('#filtroTextoDocumentos').val('');
               $('#contenedorCotizaciones').empty();
               cargarCotizacionesSucursal();
		   }else if(e.keyCode==112){
               e.preventDefault();
			   $('#buscarPagos').modal('toggle');
			   cargarTiposPago();
		   }else if(e.keyCode==115){
                e.preventDefault();
               $('#buscarClientes').modal('toggle');
               $('#filtroTextoClientes').val('');
               $('#contenedorClientes').empty();
           }else if(e.keyCode==118){
        	   e.preventDefault();
	   			$('#filtroTextoProductos').val('');
	   			$('#contenedorProductos').empty();
	   			$('#buscarProductos').modal('toggle');
           }
	   });
	   /**EVENTOS*/
	   fechaActual();
	   $('#nit').focus();
	   $('#tDoc').keydown(function(e){
		   if(e.keyCode==13){
			   cargarDatosDoc(1,$(this).val());
		   }
		   
//		   if(e.keyCode==13){
//			   if($(this).val()==3 || $(this).val()==1){
//				   $('#autorizar').modal('toggle');
//			   }else if($(this).val()==2){
//				   cargarDatosDoc(1,$(this).val());
//			   }
//			   
//		   }
	   });
	   $('#fPago').keydown(function(e){
		   if(e.keyCode==13){
			   cargarDatosPago(3,$(this).val(),$('#lCredito').val());
			   
		   }
		   
	   });
	   
	   $('#nit').keydown(function(e){
		   if(e.keyCode==13){
			   cargarDatosNit($(this).val());
		   }
	   });
        $(document).on('keydown', '#porcentaje', function (e){
            if(e.keyCode==13){
                var indiceFila = $(this).parent().parent().index();
                $('#indice').text(indiceFila);
                codigoP = $('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(0).children().val();
	   			$('#codigoProd').text(codigoP);
	   			var porcentaje = $('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(6).children().val();
	   			var dm = $('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(11).children().val();
	   			var im = $('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(8).children().val();
	   			
	   			if(porcentaje == ' '){
	   				
	   			}else if(porcentaje > dm){
	   				$('#autorizar').modal('toggle');
	   			}else{
	   				temporal = separarTexto(1, $('#subTotal').text());
	   				var porCan = parseFloat(im * (porcentaje/100)).toFixed(2);
	   				$('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(7).children().val(porCan);
	   				$('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(8).children().val(im-porCan);
	   				alert(im-porCan);
	   				$('#subTotal').text('SubTotal: ' + (im-porCan));
	   				$('#total').text('Total: ' + (im-porCan));
	   				
	   			}
            }
        });
	   $('#siBodegas').click(function(){
		   $('#botones').hide();
		   $('#escondido').show();
		   $('#contenedorProductosBodegas').empty();
		   var cp;
		   cp = $('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(0).text();
		   cargarProductosFiltroBodegas($('#codigoLista').text(), "", separarTexto(0, $('#fPago').val()), cp);
	   });
	   $(document).on('click', '.codigoProducto', function (){
		   if($('#fPago').val() == ''){
			   	alert('Debe ingresar una forma de pago antes de buscar cualquier producto');/***INCLUIR EN MODAL DE ERRORES ***/
	   			$('#divFormaPago').addClass('has-error');
	   			$('#fPago').focus();
	   			$('#indice').text($(this).parent().index());
		   }else{
			   $('#indice').text($(this).parent().index());
			   console.log($('#indice').text());
			   $('#divFormaPago').removeClass('has-error');
			   $('.codigoProducto').editable(function(value, settings) {
				     return(value);
				  }, {
				     onblur  : 'cancel',
				     event   : 'dblclick',
				     style   : 'inherit',
				     callback : function(value, settings) {
				    	 traerProducto(value, separarTexto(0, $('#fPago').val()), $('#codigoLista').text() ,$('#indice').text());
				    	 console.log($(this).parent().index());
				     }
				  });
		   }
	   });
	   $(document).on('click', '.cantidad', function (){
		   $('#indice').text($(this).parent().index());
		   var indice;
		   indice = $(this).parent().index();
		   $('.cantidad').editable(function(value, settings) {
			     return(value);
			  }, {
			     onblur  : 'cancel',
			     event   : 'dblclick',
			     style   : 'inherit',
			     callback : function(value, settings) {
			    	 
			    	 ejecutarCantidad(value, indice);
			     }
			  });
	   });
//	   $(document).on('keydown', '.codigoProducto', function(e){
//		   if(e.keyCode==9){
//			   $('#datosVarios > tbody > tr').eq($('#indice').text()).find('.codigoProducto').blur();
//			   $('.cantidad').editable(function(value, settings) {
//				     return(value);
//				  }, {
//				     onblur  : 'cancel',
//				     event   : 'dblclick',
//				     style   : 'inherit',
//				     callback : function(value, settings) {
//				    	 
//				    	 ejecutarCantidad(value, $('#indice').text());
//				     }
//				  });
//			   console.log('teclas presionadas');
//	 			$('#datosVarios > tbody > tr').eq($('#indice').text()).find('.cantidad').trigger('dblclick');
//		   }
//	   });
//	   $(document).on('click', '.descripcion', function (){
//		   $('.descripcion').popover({
//			   	trigger: 'hover',
//		        placement: 'top',
//		        content: function() {
//		           var message = $(this).text();
//		             return message;
//		        }
//		    });
//	   });
	   $('#codigoProduct').keydown(function (e){

	   		alert('presionado');
		   	if(e.keyCode==13){
		   		if($.trim($('#fPago').val()) == ''){
		   			alert('Debe ingresar una forma de pago');
		   			$('#divFormaPago').addClass('has-error');
		   			$('#fPago').focus();
		   		}else{
		   			var indiceFila = $(this).parent().parent().index();
		   			$('#indice').text(indiceFila);
		   			var codigo = $(this).val();
			   		var lista = 1;
			   		var formaPago = separarTexto(0, $('#fPago').val());
			   		var $td = $(this).closest('tr').children('td');
//			   		var $td= $(this).closest('tr').children('td').children('input');
			   		var $td2= $(this).closest('tr').children('td').children('div').children('input');
			   		$.post('TraerProducto',{codigo :codigo, lista : lista, formaPago : formaPago} ,function(responseJson){
			 		   if(responseJson!=null){
			 			   $.each(responseJson, function(key, value) {
			 				   $td.eq(0).val($.trim(value['codigoProducto']));
			 				   $('#codigoProd').text($.trim(value['codigoProducto']));
			 				   $td.eq(1).val(value['medida']);
			 				   $td.eq(2).val(value['descripcionProducto']);
			 				   $td.eq(4).val(parseInt(value['disponible']));
			 				   $td.eq(5).val(value['precioUnitario']);
			 				   $td.eq(10).val(value['descuentoMaximo']);
			 				   $td.eq(6).val(parseFloat(value['descuento']).toFixed(2));
			 				   $td.eq(7).val(parseFloat(value['importe']).toFixed(2));
			 				   $td.eq(8).val(value['codigoBodega']);
			 				   var esKit = value['esKit'];
			 				  $('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(13).text(value['esKit']);
			 				  
			 				   if(esKit=='S'){
			 					  
			 					  $('#datosVarios > tbody > tr').eq($('#indice').text()).css( "background-color", "#F89406" );
			 				   }else if(esKit=='N'){
			 					  $('#datosVarios > tbody > tr').eq($('#indice').text()).css( "background-color", "#f5f5f5" );
			 					  
			 				   }
			 			    });
			 			  var test = $('#datosVarios > tbody > tr').eq($('#indice').text()).find('#1').children().val();
			   				alert(test);
			 			  alert($('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(13).text());
			 			  $td.eq(3).focus();
			 		   }
			 				   
			 	   });
		   		}
		   		
	   		}else if(e.keyCode==118){
	   			e.preventDefault();
	   			$('#filtroTextoProductos').val('');
	   			var indiceFila = $(this).parent().parent().index();
	   			$('#indice').text(indiceFila);
	   			
	   			//modificar la primera fila
//	   			$('table#datosVarios tbody tr:first td:first').children().val(indiceFila);
	   			$('#contenedorProductos').empty();
	   			$('#buscarProductos').modal('toggle');
	   		}else if (e.keyCode==117){
	   			e.preventDefault();
	   			if($(this).val()==''){
	   				alert('Debe ingresar un producto antes de ver los detalles de kit');
	   				
	   			}else{
	   				
	   				$('#detallesKit').modal('toggle');
		   			$('#contenedorKits').empty();
		   			var uni = $('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(1).children().val();
		   			var bod = $('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(9).children().val();
		   			var serie = '';
		   			var numero = '';
		   			cargarKitsProducto($('#codigoLista').text(), uni, separarTexto(0, $('#fPago').val()), $('#codigoProd').text(), bod, serie, numero);
	   			}
	   			
	   		}
	   });
	   $('#autorizacion').click(function (e){
		   autorizarDocumento($('#tDoc').val());
	   });
	   
	   $('#agregarFila').click(function (e){
//		   $('#datosVarios tbody tr').each(function (index){
//			   $('#datosVarios > tbody > tr').eq(index).find('.codigoProducto').text();
//		   });
		   	 agregarFila();
           });
	   
	   $('#f3').click(function (e){
		   $('#buscarDocumentos').modal('toggle');
           $('#filtroTextoDocumentos').val('');
           $('#contenedorCotizaciones').empty();
           cargarCotizacionesSucursal();
	   });
	   $('#f1').click(function (e){
		   $('#buscarPagos').modal('toggle');
		   cargarTiposPago();
	   });
        $('#f4').click(function (e){
		   $('#buscarClientes').modal('toggle');
		   $('#contenedorClientes').empty();
            $('#filtroTextoClientes').val('');
	   });
	 //seleccionar el tipo de pago desde tabla en modal
	   $jq("table[id$='tablaPagos'] td:nth-child(1)").live('click',function(event) {  
				//Para evitar que el link actue.  
		   event.preventDefault();  
		   var $td= $(this).closest('tr').children('td');
		   $('#fPago').val($td.eq(0).text());
		   cargarDatosPago(3,$('#fPago').val(),'0.1');
		   $('#buscarPagos').modal('toggle');
	   });
	   //seleccionar producto
	   $jq("table[id$='tablaProductos'] td").live('click',function(event) {  
			//Para evitar que el link actue.  
		   event.preventDefault();  
		   var $td= $(this).closest('tr').children('td');
		   traerProducto($td.eq(0).text(), separarTexto(0, $('#fPago').val()), $('#codigoLista').text() ,$('#indice').text());
		   
		   $('#buscarProductos').modal('toggle');
		   
		   
	   });
	   
	   
	   //cargar productos por filtros
	   $('#filtroTextoProductos').keydown(function (e){
		   if(e.keyCode==13){
			   
			   $('#contenedorProductos').empty();
			   if($(this).val()==''){
				   alert('Debe ingresar algo para buscar');
			   }else{
				   cargarProductosFiltro($('#filtroComboProductos').val(),$('#filtroTextoProductos').val(),  separarTexto(0, $('#fPago').val()));
			   }
		   }
		   
		   
	   });	
	   $('#filtroTextoBodegas').keydown(function (e){
		   
		   if(e.keyCode==13){
			   $('#contenedorProductosBodegas').empty();
			   if($(this).val()=='' || $('#filtroComboBodegas').val() == ''){
				   alert('Las busquedas requieren un filtro y una palabra a buscar.');
			   }else{
				   
				   cargarProductosFiltroBodegas($('#filtroComboBodegas').val(), $(this).val(), separarTexto(0, $('#fPago').val()), codigoP);
			   }
		   }
		   
	   });
        $('#filtroTextoClientes').keydown(function (e){
            if(e.keyCode==13){
                if($(this).val()=='' || $('#filtroComboClientes').val() == ''){
                    alert('Para buscar un cliente debe seleccionar un filtro y un texto');
                }else{
                    cargarFiltroClientes($('#filtroComboClientes').val(), $(this).val());
                }
                
            }
        });
        $('#filtroTextoDocumentos').keydown(function (e){
            if(e.keyCode==13){
            	$('#contenedorCotizaciones').empty();
                if($(this).val()=='' || $('#filtroComboDocumentos').val() == ''){
                    alert('Para buscar un cliente debe seleccionar un filtro y un texto');
                }else{
                    cargarCotizacionesSucursal($(this).val(), $('#filtroComboDocumentos').val());
                }
                
            }
        });
	   //Seleccionar producto de Bodega alterna
	   
	   $jq("table[id$='tablaProductosBodega'] td").live('click',function(event) {  
			//Para evitar que el link actue.  
		   event.preventDefault();  
		   var $td= $(this).closest('tr').children('td');
		   $('#datosVarios > tbody > tr').eq($('#indice').text()).find('.disponible').text($td.eq(4).text());
		   console.log($('#indice').text());
		   console.log($td.eq(4).text());
		   $('#datosVarios > tbody > tr').eq($('#indice').text()).find('.bodega').text($td.eq(5).text());
		   console.log($td.eq(5).text());
		   
		   $('#buscarProductosBodegas').modal('toggle');
		   //enfocar cantidad
		   
		   $('#datosVarios > tbody > tr').eq($('#indice').text()).children().eq(3).children().val();
		   
		   //hacer la suma
		   var resultado;
		   resultado = parseFloat($('#datosVarios > tbody > tr').eq($('#indice').text()).find('.cantidad').text()*
				   				$('#datosVarios > tbody > tr').eq($('#indice').text()).find('.precio').text()
		   ).toFixed(2);
		   
		   
		   $('#datosVarios > tbody > tr').eq($('#indice').text()).find('.importe').text(resultado);
		   sumarColumnaImporte();
	   });
        
        
        /**SELECCIONAR EL CLIENTE**/
        $jq("table[id$='tablaClientes'] td:nth-child(1)").live('click',function(event) {
            event.preventDefault(); 
            var $td= $(this).closest('tr').children('td');
            $('#buscarClientes').modal('toggle');
            cargarDatosNit($td.eq(0).text());
	   });
        
        $jq("table[id$='tablaCotizaciones'] td").live('click',function(event) {
            event.preventDefault(); 
            var $td= $(this).closest('tr').children('td');
            $('#buscarDocumentos').modal('toggle');
            cargarEncabezado(1, '', $.trim($td.eq(0).text()));
	   });
	   //grabar el Documento
	   $('#grabarDocumento').click(function(){
		   var numFilas = $('#datosVarios >tbody >tr').length;
		   
			   $('#numFilas').text(numFilas);
			   $.post('IngresarEnc',{
				   codigoCliente : $('#codigoCliente').text(), nit : $('#nit').val(), nombreCliente : $('#nombre').val(),
				   direcFactura : $('#direcF').val(), tel : $('#telefono').val(), tarjeta : $('#tarjeta').val(),
				   direcEnvio : $('#direcE').val(), tipoDoc : separarTexto(0, $('#tDoc').val()), fechaVence : $('#fecha').text(),
				   tipoPago : separarTexto(0, $('#fPago').val()), tipoCredito : $('#tCredito').val(), autoriza : "S",
				   fechaDoc : $('#fecha').text(), cargosEnvio : 0, otrosCargos: 0,
				   montoVenta: separarTexto(1, $('#subTotal').text()), montoTotal : separarTexto(1, $('#total').text()), tipoNota : 0,
				   caja : 0 , fechaEntrega : $('#fecha').text(), noConsigna : 0 , codMovDev : 0,
				   generaSolicitud : 'N', tipoPagoNC : 0 , tipoCliente : $('#tipoCliente').text(),
				   codigoNegocio : "", cantidadDevolver : 0, autorizoDespacho : "", 
				   saldo : $('#saldoCliente').text()
			   } ,function(responseText) {
				   if(responseText!=null){
					   var numDocumento;
					   numDocumento = $.trim(responseText);
					   $('#numDocumento').text(numDocumento);
					   guardarDetalle(numDocumento);
					   console.log('Documento Creado ' +  numDocumento);
				   }   
			   });
		   

//			   guardarDetalle(57074);
//		   guardarDetalle(57043);
		   
//		   
	   });
	   
   });/**fin de document.ready*/
    function cargarDetalle(tipoDoc, serie, numDocumento){
    	$.post('CargarDetalle',{tipoDoc : tipoDoc, serie : serie, numDocumento : numDocumento} ,function(responseJson) {
    		var fila;
	 		   if(responseJson!=null){
	 			  $('#datosVarios tbody').empty();
	 			  fila = 0;
	 			   $.each(responseJson, function(key, value) {
	 				  
	 				  var rowNew = $('<tr>' +
	 		            		'<td class="codigoProducto"></td>'+
	 		            		'<td class="medida"></td>'+
	 		            		'<td class="descripcion"><div class="contenDescrip"></div></td>'+
	 		            		'<td class="cantidad"></td>'+
	 		            		'<td class="disponible"></td>'+
	 		            		'<td class="precio"></td>'+
	 		            		'<td class="porcentaje"></td>'+
	 			   				'<td class="descuento" ></td>'+
	 		            		'<td class="importe"></td>'+
	 		            		'<td class="bodega"></td>'+
	 		            		'<td class="envio"><input type="checkbox"></td>'+
	 		            		'<td class="dm" ></td>'+
	 		            		'<td class="obser"></td>'+
	 		            		'<td class="kit"></td>'+
	 		            	'</tr>');
	 				  	rowNew.appendTo($('table#datosVarios tbody'));
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.codigoProducto').text(value['codigoProducto']);
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.medida').text(value['uMedida']);
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.descripcion').children().text(value['descripcion']);
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.cantidad').text(parseInt(value['cantidad']));
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.precio').text($.trim(parseFloat(value['precio']).toFixed(2)));
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.disponible').text(parseInt(value['disponible']));
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.porcentaje').text(parseFloat(value['porDescuento']).toFixed(2));
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.descuento').text($.trim(parseFloat(value['descuento']).toFixed(2)));
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.bodega').text(value['bodega']);
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.importe').text($.trim(parseFloat(value['total']).toFixed(2)));
	 				  	console.log(value['envia']);
	 				  	if(value['envia']=='1'){
	 				  		$('#datosVarios > tbody > tr').eq(fila).find('.envio').children().eq(0).prop('checked', true);
	 				  	}else if(value['envia']=='0'){
	 				  		$('#datosVarios > tbody > tr').eq(fila).find('.envio').children().eq(0).prop('checked', false);
	 				  	}
	 				  	
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.obser').text(value['observaciones']);
	 				  	$('#datosVarios > tbody > tr').eq(fila).find('.kit').text(value['kit']);
		               console.log($('#datosVarios > tbody > tr').eq(fila).find('.kit').text());
	 				  	fila += 1;
		               
	 			    });
	 			  $('.kit').hide();
	 		   }
	 				   
	 	   });
    	
    }
    function cargarEncabezado(tipoDoc, serie, numDocumento){
    	$.post('CargarEncabezado',{tipoDoc : tipoDoc, serie : serie, numDocumento : numDocumento} ,function(responseJson) {
 		 		   if(responseJson!=null){
 		 			   $.each(responseJson, function(key, value) {
 						   $('#fPago').val($.trim(value['tipoPago']));
 						   $('#tCredito').val($.trim(value['tipoCredito']));
 						   $('#tDoc').val(value['tipoDocumento']);
 						   $('#nDoc').val(numDocumento);
 						   $('#fechaVencimiento').val(componerFecha(value['fechaVence']));
 						   $('#nit').val(value['nit']);
 						   console.log(value['nit']);
 						   $('#direcF').val(value['direcFactura']);
 						   $('#nombre').val(value['nombreCliente']);
 						   $('#codigoCliente').text(value['codigoCliente']);
 						   $('#autoriza').text(value['autoriza']);
 						   $('#subTotal').text('SubTotal: ' + $.trim(parseFloat(value['montoVenta']).toFixed(2)));
 						   $('#total').text('Total: ' + $.trim(parseFloat(value['montoTotal']).toFixed(2)));
 		 			    });
 		 			   
 		 			   cargarDatosNit($('#nit').val());
 		 			 cargarDetalle(1,'',$('#nDoc').val());
 		 		   }
 		 				   
 		 	   });
    }
    function guardarDetalle(numeroDocumento){
    	console.log('Documento pasado: ' + numeroDocumento);
    	 $('#datosVarios tbody tr').each(function (index){
	    		var codigoP,descripcion,medida,cantidad,disponible,precioU, porDesc, descuento, importe, dm, observ,envio
				   var bod, kit;
				   codigoP = $('#datosVarios > tbody > tr').eq(index).find('.codigoProducto').text();
				   console.log('Producto a guardar: ' +codigoP);
				   descripcion = $('#datosVarios > tbody > tr').eq(index).find('.descripcion').text();
				   medida = $('#datosVarios > tbody > tr').eq(index).find('.medida').text();
				   cantidad = $('#datosVarios > tbody > tr').eq(index).find('.cantidad').text();
				   disponible = $('#datosVarios > tbody > tr').eq(index).find('.disponible').text();
				   precioU = $('#datosVarios > tbody > tr').eq(index).find('.precio').text();
				   if($('#datosVarios > tbody > tr').eq(index).find('.porcentaje').text()==''){
					   porDesc = 0.00;
				   }else{

					   porDesc = $('#datosVarios > tbody > tr').eq(index).find('.porcentaje').text();
				   }
				   descuento = $('#datosVarios > tbody > tr').eq(index).find('.descuento').text();
				   importe = $('#datosVarios > tbody > tr').eq(index).find('.importe').text();
				   bod = $('#datosVarios > tbody > tr').eq(index).find('.bodega').text();
				   if($('#datosVarios > tbody > tr').eq(index).find('.envio').children().prop('checked')){
					   envio = 1;
				   }else{
					   envio = 0;
				   }
				   dm = $('#datosVarios > tbody > tr').eq(index).find('.dm').text();
				   observ = $('#datosVarios > tbody > tr').eq(index).find('.obser').text();
				   kit = $('#datosVarios > tbody > tr').eq(index).find('.kit').text();
				   
				   $.post('IngresarDet',{
					   tipoDocumento : separarTexto(0, $('#tDoc').val()), serieDocumento : '', numeroDocumento : numeroDocumento, numCorrelativo : 1, codigoProducto : codigoP,
					   UMedida : medida, cantidad: $('#datosVarios > tbody > tr').eq(index).find('.cantidad').text(), precio : precioU, porDescuento : porDesc, descuento : descuento, total: importe, codigoCliente : $('#codigoCliente').text(),
					   promo : 0, bodega : bod, envio : envio, observaciones : observ, lista : $('#codigoLista').text(), pago : separarTexto(0, $('#fPago').val()), kit : kit, corrKit : 0, codPromo : 0,
					   serieDevProy : '', numDevProy : '0', ordenCompra : 0
					   
					   
				   } ,function(responseText) {
					   return false;
					   if(responseText!=null){
//						   alert(responseText);
						   console.log(responseText);
						   return true;
//						   alert('Producto grabado en documento numero: ' + $('#numDocumento').text());
					   }
							   
				   });
			   });
    	
    }
   function traerProducto(codigoProducto, tipoPago, lista, indiceFila){
	   console.log(indiceFila);
	   $('#indice').text(indiceFila);
	    
        $.post('TraerProducto',{codigo : codigoProducto, lista : lista, formaPago : tipoPago} ,function(responseJson){
		 		   if(responseJson!=null){
		 			   $.each(responseJson, function(key, value) {
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.codigoProducto').text($.trim(value['codigoProducto']));
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.medida').text(value['medida']);
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.descripcion').children().text(value['descripcionProducto']);
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.cantidad').text(parseInt(0));
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.disponible').text(parseInt(value['disponible']));
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.precio').text(parseFloat(value['precioUnitario']).toFixed(2));
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.porcentaje').text(parseFloat(0).toFixed(2));
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.descuento').text(parseFloat(0).toFixed(2));
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.importe').text(parseFloat(0).toFixed(2));
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.bodega').text(value['codigoBodega']);
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.dm').text(value['descuentoMaximo']);
		 				   var esKit = value['esKit'];
		 				   $('#datosVarios > tbody > tr').eq(indiceFila).find('.kit').text(value['esKit']);
		 				   console.log($('#datosVarios > tbody > tr').eq(indiceFila).find('.kit').text());
		 				   if(esKit=='S'){
		 					  $('#datosVarios > tbody > tr').eq(indiceFila).css({"color": "#e75505", "font-weight": "bold"});
		 				   }else if(esKit=='N'){
		 					  $('#datosVarios > tbody > tr').eq(indiceFila).css({"color": "#000000", "font-weight": "normal"});
		 					  
		 				   }
		 			    });
		 			  sumarColumnaImporte();
		 			 $('.cantidad').editable(function(value, settings) {
		 				 $('#datosVarios > tbody > tr').eq(indiceFila).find('.cantidad').text('');
		 				 console.log(value);
					     return(value);
					  }, {
					     onblur  : 'cancel',
					     event   : 'dblclick',
					     style   : 'inherit',
					     callback : function(value, settings) {
					    	 
					    	 ejecutarCantidad(value, indiceFila);
					     }
					  });
		 			$('#datosVarios > tbody > tr').eq(indiceFila).find('.cantidad').trigger('dblclick');
		 		   }
		 				   
		 	   });
   }
   function ejecutarCantidad(cantidad, indiceFila){
	   console.log('indice Fila en cantidad: ' + indiceFila);
	   revisarCantidadMismoProducto($('#datosVarios > tbody > tr').eq(indiceFila).find('.codigoProducto').text(), cantidad, indiceFila);
	   $('#indice').text(indiceFila);
	   var importe;
	   var cantDisp;
	   var cp;
	   cantDisp = parseInt($('#datosVarios > tbody > tr').eq(indiceFila).find('.disponible').text());
	   cp = $('#datosVarios > tbody > tr').eq(indiceFila).find('.codigoProducto').text();
	   if(cantDisp < cantidad){
		   	$('#botones').show();
  			$('#escondido').hide();
  			$('#buscarProductosBodegas').modal('toggle');
  			$('#contenedorProductosBodegas').empty();
	   }else{
		   
	   }
	   
	   
   }
   function sumarColumnaImporte(){
	   var sum = 0;
	    $('.importe').each(function() {
	        sum += Number($(this).text());
	    });
	    console.log(sum);
	    $('#subTotal').text('SubTotal: ' + parseFloat(sum).toFixed(2));
	    $('#total').text('Total: ' + parseFloat(sum).toFixed(2));

   }
   function obtenerImporteSinDescuento(){
	   
   }
   /**FUNCIONES*/
   //fecha actual
   function fechaActual(){
	   	var d = new Date();
	    var month = d.getMonth()+1;
	    var day = d.getDate();
	    var fecha = 
	        ((''+day).length<2 ? '0' : '') + day + '/' +
	        ((''+month).length<2 ? '0' : '') + month + '/' +
	        d.getFullYear();
		$('#fecha').text(fecha);
		
   }
 //fecha entrega
   function fechaEntrega(){
	   	var d = new Date();
	    var month = d.getMonth()+1;
	    var day = d.getDate()+1;
	    var fecha = 
	    	d.getFullYear() + '-' + 
	        ((''+month).length<2 ? '0' : '') + month + '-' +
	        ((''+day).length<2 ? '0' : '') + day;  
	        
		$('#fechaEntrega').val(fecha);
		
   }
   //FUNCION CARGAR COMBO TIPOS DE PAGO
   function cargarTiposPago(){
	   $.post('CargarCombos',{combo : 1} ,function(responseText) {
		   if(responseText!=null){
			   $('#tCredito').html(responseText);
		   }
				   
	   });
   }
   
   //funcion cargar datos usando Nit
   function cargarDatosNit(nit){
	   $.post('InformacionNit',{nit : nit} ,function(responseJson) {
		   if(responseJson!=null){
			   $.each(responseJson, function(key, value) {
					$('#nit').val(value['nit']);
					$('#nombre').val(value['nombreCliente']);
					$('#direcF').val(value['direccionF']);
					$('#direcE').val(value['direccionE']);
					$('#telefono').val(value['telefono']);
					$('#lCredito').val(value['limiteCredito']);
					$('#codigoCliente').text(value['codigoCliente']);
					$('#tipoCliente').text(value['tipoCliente']);
					$('#saldoCliente').text(value['saldo']);
			    });
		   }
				   
	   });
   }
   //funcion cargar datos del tipo documento
   function cargarDatosDoc(opcion, codigoDocumento){
	   $.post('TraeDatos',{opcion : opcion,codigo : codigoDocumento} ,function(responseJson) {
		   
		   if(responseJson!=null){
			   $.each(responseJson, function(key, value) { 
					$('#tDoc').val(value['codigoMovimiento'] + ' ' + value['descripcionMovimiento']);
			    });
			   cargarFechaVencimiento(2, separarTexto(0, $('#tDoc').val()));
		   }
				   
	   });
   }
 //funcion cargar datos del tipo documento
   function cargarDatosPago(opcion, tipoPago, limiteCredito){
	   $.post('TraeDatos',{opcion : opcion,tipoPago : tipoPago,limite : limiteCredito} ,function(responseJson) {
		   if(responseJson!=null){
			   $.each(responseJson, function(key, value) { 
					$('#fPago').val(value['codigoPago'] + ' ' + value['descripcionPago']);
					$('#tCredito').val(value['esCredito']);
					
					$('.codigoProducto').editable(function(value, settings) {
					     return(value);
					  }, {
					     onblur  : 'cancel',
					     event   : 'dblclick',
					     style   : 'inherit',
					     callback : function(value, settings) {
					    	 traerProducto(value, separarTexto(0, $('#fPago').val()), $('#codigoLista').text() ,$('#indice').text());
					     }
					  });
					$('#datosVarios > tbody > tr').eq($('#indice').text()).find('.codigoProducto').trigger('dblclick');
			    });
		   }
				   
	   });
   }
  
   //cargar fecha
   function cargarFechaVencimiento(opcion, noDoc){
	   $.post('TraeDatos',{opcion : opcion , codigo : noDoc} ,function(responseJson) {
		   if(responseJson!=null){
			   $.each(responseJson, function(key, value) {
					$('#fechaVencimiento').val(componerFecha(value['fechaVencimiento']));
			    });
		   }
				   
	   });
   }
   //Autorizaciones varias
   function autorizarDocumento(noDoc){
	   var autorizado = false;
	   $.post('Autorizaciones',{opcion : noDoc} ,function(responseText) {
		   if(responseText!=null){
			   if(parseInt(responseText)==1){
				   autorizado = true;
			   }else{
				   false
			   }
		   }
				   
	   });
	   return autorizado;
   }
   
 //Autorizar tipo documento
   function autorizarDocumento(noDoc){
	   $.post('Autorizaciones',{opcion : noDoc} ,function(responseText) {
		   if(responseText!=null){
			   if(parseInt(responseText)==1){
				   cargarDatosDoc(1, noDoc);
				   $('#autorizar').modal('toggle');
			   }else{
				   ('#notificacion').text(responseText);
			   }
		   }
				   
	   });
   }
   function separarTexto(posicion, texto){
	   var cadena = texto;
	   var particiones = cadena.split(' ');
	    
	   return particiones[posicion];
   }
   function componerFecha(fecha){
	   var cadena = fecha;
	   var particiones = cadena.split('-');
	   var nuevaFecha;
	   cadena = particiones[2];
	   var particiones2 = cadena.split(' ');
	   nuevaFecha = particiones2[0] + '/' + particiones[1] + '/' + particiones[0];
	   return nuevaFecha;
   }
   function obtenerCodigo(codigo){
	   var codigoProducto = codigo;
	   return codigoProducto;
   }
   function cargarTiposPago(){

	   $('#contenedorPagos').empty();
	   $.post('CargaTiposPago', function(responseJson) {
			   if(responseJson!=null){
				   var contenedor = $("#contenedorPagos");
				   var tabla = $("<table id='tablaPagos' class='table table-striped table-bordered table-condensed'></table>");
			       var thead = $("<thead></thead>");
			       var tbody = $("<tbody></tbody>");
			       var encabezado = $("<tr> <th></th> <th></th> </tr>");
			       encabezado.children().eq(0).text("Codigo Pago");
			       encabezado.children().eq(1).text("Descripcion");
			       
			       encabezado.appendTo(thead);
			       tabla.appendTo(contenedor);
			       
			       thead.appendTo(tabla);
			       tbody.appendTo(tabla);
			       $.each(responseJson, function(key,value) {
			            var rowNew = $("<tr> <td><a href='#'></a></td> <td></td> </tr>");
			               rowNew.children().children().eq(0).text(value['codigo']);
			               rowNew.children().eq(1).text(value['descripcion']);
			               rowNew.appendTo($('table#tablaPagos tbody'));
			       });
			       $('#tablaPagos').dataTable( {
			    	   "scrollY" : 200,
			    	   "scrollX" : true,
				        "language": {
				            "url": "//cdn.datatables.net/plug-ins/1.10.7/i18n/Spanish.json"   	
				        }
			       	
				    });
			       }
			   });
   }
   function cargarProductosFiltro(seleccion, texto, codigoPago){
	   $('#contenedorProductos').empty();
	   $.post('FiltrosProducto',{seleccion : seleccion, criterio : texto, codigoPago : codigoPago}, function(responseJson){
		   if(responseJson!=null){
			   var contenedor = $("#contenedorProductos");
			   var tabla = $("<table id='tablaProductos' class='table table-striped table-bordered table-condensed table-hover'></table>");
		       var thead = $("<thead></thead>");
		       var tbody = $("<tbody></tbody>");
		       var encabezado = $("<tr> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th>  <th></th> <th></th> <th></th> <th></th> </tr>");
		       encabezado.children().eq(0).text("Código");
		       encabezado.children().eq(1).text("Descripcion");
		       encabezado.children().eq(2).text("Marca");
		       encabezado.children().eq(3).text("Precio U");
		       encabezado.children().eq(4).text("Disponible");
		       encabezado.children().eq(5).text("Bodega");
		       encabezado.children().eq(6).text("Back Order");
		       encabezado.children().eq(7).text("Fecha Espera");
		       encabezado.children().eq(8).text('Tránsito');
		       encabezado.children().eq(9).text("Familia");
		       encabezado.children().eq(10).text("referencia");
		       encabezado.appendTo(thead);
		       tabla.appendTo(contenedor);
		       thead.appendTo(tabla);
		       tbody.appendTo(tabla);
		       $.each(responseJson, function(key,value) {
		            var rowNew = $("<tr> <td><a href='#'></a></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>");
		               rowNew.children().children().eq(0).text(value['codigoProducto']);
		               rowNew.children().eq(1).text(value['descripcionProducto']);
		               rowNew.children().eq(2).text(value['marcaProducto']);
		               rowNew.children().eq(3).text(value['precioProducto']);
		               rowNew.children().eq(4).text(parseInt(value['disponible']));
		               rowNew.children().eq(5).text(value['bodegaProducto']);
		               rowNew.children().eq(6).text(parseInt(value['backOrder']));
//		               if(typeof value['fechaespera'] == 'undefined'){
//		            	   rowNew.children().eq(7).text('N/A');
//		               }else{
		            	   rowNew.children().eq(7).text(value['fechaespera']);
//		               }
		               rowNew.children().eq(8).text(value['transito']);
		               rowNew.children().eq(9).text(value['familiaProducto']);
		               rowNew.children().eq(10).text(value['referenciaProducto']);
		               rowNew.appendTo($('table#tablaProductos tbody'));
		       });
		       $("#tablaProductos").dataTable( {
		    	   "columnDefs": [
			                       { "width": "200%", "targets": 1 }
			                     ],
		    	   "scrollY" : 200,
		    	   "scrollX" : true,
			        "language": {
			            "url": "//cdn.datatables.net/plug-ins/1.10.7/i18n/Spanish.json"   	
			        }
			        
			    });
		       }
	   });
   }
   
   function cargarProductosFiltroBodegas(codigoLista, texto, codigoPago, codigoProducto){
	   console.log(codigoLista + ' ' + texto + ' ' + codigoPago + ' ' + codigoProducto);
	   $('#contenedorProductosBodegas').empty();
	   $.post('ProdOtrasBod',{codigoLista : codigoLista, criterio : texto, codigoPago : codigoPago, codigoProducto : codigoProducto}, function(responseJson){
		   
		   if(responseJson!=null){
			   var contenedor = $("#contenedorProductosBodegas");
			   var tabla = $("<table id='tablaProductosBodega' class='table table-striped table-bordered table-condensed table-hover'></table>");
		       var thead = $("<thead></thead>");
		       var tbody = $("<tbody></tbody>");
		       var encabezado = $("<tr> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> </tr>");
		       encabezado.children().eq(0).text("Codigo");
		       encabezado.children().eq(1).text("Descripcion");
		       encabezado.children().eq(2).text("Marca");
		       encabezado.children().eq(3).text("Precio U");
		       encabezado.children().eq(4).text("Disponible");
		       encabezado.children().eq(5).text("Bodega");
		       encabezado.children().eq(6).text("Familia");
		       encabezado.children().eq(7).text("Referencia");
		       encabezado.appendTo(thead);
		       tabla.appendTo(contenedor);
		       thead.appendTo(tabla);
		       tbody.appendTo(tabla);
		       $.each(responseJson, function(key,value) {
		            var rowNew = $("<tr> <td><a href='#'></a></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>");
		               rowNew.children().children().eq(0).text(value['codigoProducto']);
		               rowNew.children().eq(1).text(value['descripcionProducto']);
		               rowNew.children().eq(2).text(value['marcaProducto']);
		               rowNew.children().eq(3).text(value['precioProducto']);
		               rowNew.children().eq(4).text(parseInt(value['disponible']));
		               rowNew.children().eq(5).text(value['bodegaProducto']);
		               rowNew.children().eq(6).text(value['familiaProducto']);
		               rowNew.children().eq(7).text(value['referenciaProducto']);
		               rowNew.appendTo($('table#tablaProductosBodega tbody'));
		       });
		       $("#tablaProductosBodega").dataTable( {
		    	   "columnDefs": [
			                       { "width": "200%", "targets": 1 }
			                     ],
		    	   "scrollY" : 200,
		    	   "scrollX" : true,
			        "language": {
			            "url": "//cdn.datatables.net/plug-ins/1.10.7/i18n/Spanish.json"   	
			        }
			        
			    });
		       }
	   });
   }
    function cargarFiltroClientes(opcion, texto){
        $.post('FiltroClientes',{opcion : opcion, criterio : texto}, function(responseJson){
		   if(responseJson!=null){
			   var contenedor = $("#contenedorClientes");
			   var tabla = $("<table id='tablaClientes' class='table table-striped table-bordered table-condensed table-hover'></table>");
		       var thead = $("<thead></thead>");
		       var tbody = $("<tbody></tbody>");
		       var encabezado = $("<tr> <th></th> <th></th> <th></th> </tr>");
		       encabezado.children().eq(0).text("Nit");
		       encabezado.children().eq(1).text("Nombre");
		       encabezado.children().eq(2).text("Tarjeta");
		       encabezado.appendTo(thead);
		       tabla.appendTo(contenedor);
		       thead.appendTo(tabla);
		       tbody.appendTo(tabla);
		       $.each(responseJson, function(key,value) {
		            var rowNew = $("<tr> <td><a href='#'></a></td> <td></td> <td></td> </tr>");
		               rowNew.children().children().eq(0).text(value['nit']);
		               rowNew.children().eq(1).text(value['nombre']);
		               rowNew.children().eq(2).text(value['tarjeta']);
		               rowNew.appendTo($('table#tablaClientes tbody'));
		       });
		       $("#tablaClientes").dataTable( {
		    	   "scrollY" : 200,
		    	   "scrollX" : true,
			        "language": {
			            "url": "//cdn.datatables.net/plug-ins/1.10.7/i18n/Spanish.json"   	
			        }
			        
			    });
		       }
	   });
    }
    function cargarCotizacionesSucursal(criterio, opcion){
        $.post('CargarCotizaciones',{criterio : criterio, opcion : opcion}, function(responseJson){
            
		   if(responseJson!=null){
			   var contenedor = $("#contenedorCotizaciones");
			   var tabla = $("<table id='tablaCotizaciones' class='table table-striped table-bordered table-condensed table-hover'></table>");
		       var thead = $("<thead></thead>");
		       var tbody = $("<tbody></tbody>");
		       var encabezado = $("<tr> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> </tr>");
		       encabezado.children().eq(0).text("No. Cotizacion");
		       encabezado.children().eq(1).text("Nombre");
		       encabezado.children().eq(2).text("Nit");
		       encabezado.children().eq(3).text("Fecha");
               encabezado.children().eq(4).text("Monto");
               encabezado.children().eq(5).text("Autorizacion");
               encabezado.children().eq(6).text("FAutorizo");
               
		       encabezado.appendTo(thead);
		       tabla.appendTo(contenedor);
		       thead.appendTo(tabla);
		       tbody.appendTo(tabla);
		       $.each(responseJson, function(key,value) {
		            var rowNew = $("<tr> <td><a href='#'></a></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>");
		               rowNew.children().children().eq(0).text(value['noCotizacion']);
		               rowNew.children().eq(1).text(value['nombre']);
		               rowNew.children().eq(2).text(value['nit']);
		               if(typeof value['fecha'] == 'undefined'){
		            	   rowNew.children().eq(3).text('N/A');
		               }else{
		            	   rowNew.children().eq(3).text(separarTexto(0, value['fecha']));
		               }
		               
		               rowNew.children().eq(4).text(value['monto']);
		               rowNew.children().eq(5).text(value['autorizacion']);
		               rowNew.children().eq(6).text(value['fAutorizacion']);
		               if(typeof value['fAutorizacion'] == 'undefined'){
		            	   rowNew.children().eq(6).text('N/A');
		               }else{
		            	   rowNew.children().eq(3).text(separarTexto(0, value['fAutorizacion']));
		               }
		               rowNew.appendTo($('table#tablaCotizaciones tbody'));
		       });
		       $("#tablaCotizaciones").dataTable( {
		    	   "scrollY" : 200,
		    	   "scrollX" : true,
			        "language": {
			            "url": "//cdn.datatables.net/plug-ins/1.10.7/i18n/Spanish.json"   	
			        }
			        
			    });
		       }
	   });
    }
    function cargarKitsProducto(lista, medida, codigoPago, codigoProducto, bodega, serie, numero){
        $.post('CargarKits',{lista : lista, medida : medida, pago : codigoPago, codigoProducto: codigoProducto, bodega: bodega, serie : serie, numero : numero}, function(responseJson){
            
		   if(responseJson!=null){
			   var contenedor = $("#contenedorKits");
			   var tabla = $("<table id='tablaKits' class='table table-striped table-bordered table-hover'></table>");
		       var thead = $("<thead></thead>");
		       var tbody = $("<tbody></tbody>");
		       var encabezado = $("<tr> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> <th></th> </tr>");
		       encabezado.children().eq(0).text("Producto");
		       encabezado.children().eq(1).text("Cod. Kit");
		       encabezado.children().eq(2).text("Descripcion");
		       encabezado.children().eq(3).text("Cod. U");
               encabezado.children().eq(4).text("Medida");
               encabezado.children().eq(5).text("Cantidad");
               encabezado.children().eq(6).text("Precio");
               encabezado.children().eq(7).text("Por Desc.");
               encabezado.children().eq(8).text("Desc.");
               encabezado.children().eq(9).text("Frac.");
               encabezado.children().eq(10).text("Disponible");
		       encabezado.appendTo(thead);
		       tabla.appendTo(contenedor);
		       thead.appendTo(tabla);
		       tbody.appendTo(tabla);
		       $.each(responseJson, function(key,value) {
		            var rowNew = $("<tr> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>");
		               rowNew.children().eq(0).text(value['codigoProducto']);
		               rowNew.children().eq(1).text(value['codigoDetKit']);
		               rowNew.children().eq(2).text(value['descripcion']);
		               rowNew.children().eq(3).text(value['unidad']);
		               rowNew.children().eq(4).text(value['medida']);
		               rowNew.children().eq(5).text(value['cantidad']);
		               rowNew.children().eq(6).text(value['precio']);
		               rowNew.children().eq(7).text(value['porDesc']);
		               rowNew.children().eq(8).text(value['descuento']);
		               rowNew.children().eq(9).text(value['frac']);
		               rowNew.children().eq(10).text(value['disponible']);
		               rowNew.appendTo($('table#tablaKits tbody'));
		               $('#tituloModalKit').text('Detalle de componentes producto: ' + value['codigoProducto']);
		       });
		       $("#tablaKits").dataTable( {
		    	   "scrollY" : 200,
		    	   "scrollX" : true,
			        "language": {
			            "url": "//cdn.datatables.net/plug-ins/1.10.7/i18n/Spanish.json"   	
			        }
			        
			    });
		       }
	   });
    }
    function cargarValorCelda(indiceFila, idCelda){
    	var valor;
    	var simbolo = '#';
    	var concatenada = simbolo.concat(idCelda);
    	alert(concatenada);
    	valor = $('#datosVarios > tbody > tr').eq(indiceFila).find(concatenada).children().val();
    	alert(valor);
    	return valor;
    }
    function revisarCantidadMismoProducto(codigoProducto, cantidad, indiceActual){
    	$('#datosVarios tbody tr').each(function (index){
    		if($('#datosVarios > tbody > tr').eq(index).find('.codigoProducto').text() == codigoProducto && index!=0 && index != indiceActual){
    			console.log('Encontro el producto en linea: ' + index);
    		}
    		
    		
		});
    }
    function agregarFila(){
    	var filaNueva = $(
	   			'<tr>' +
            		'<td class="codigoProducto"></td>'+
            		'<td class="medida"></td>'+
            		'<td class="descripcion"><div class="contenDescrip"></div></td>'+
            		'<td class="cantidad"></td>'+
            		'<td class="disponible"></td>'+
            		'<td class="precio"></td>'+
            		'<td class="porcentaje"></td>'+
	   				'<td class="descuento" ></td>'+
            		'<td class="importe"></td>'+
            		'<td class="bodega"></td>'+
            		'<td class="envio"><input type="checkbox"></td>'+
            		'<td class="dm" ></td>'+
            		'<td class="obser"></td>'+
            		'<td class="kit"></td>'+
        		'</tr>'
	   	 );
	   	 
	   	 filaNueva.prependTo(('#datosVarios > tbody'));
	   	 $('.kit').hide();
    }
    
  }(window.jQuery, window, document));