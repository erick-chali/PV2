(function($, window, document) {
   $(function() {
	   	$tabla = $('#datosVarios');

	   	$.fn.editable.defaults.mode = 'inline';
		$(window).resize(function () {
	           $('#datosVarios').bootstrapTable('resetView');
	       });
		$tabla.on('onResizableResize', function(e){
			$('#datosVarios').bootstrapTable('resetView');
		});
		$tabla.on('editable-save.bs.table', function(e, field, row, oldValue, $el){
//			console.log(JSON.stringify(e));
//			console.log(field);
//			console.log(row.codigoProducto);
//			console.log('viejo: '+oldValue);
//			console.log('elemento: '+JSON.stringify($el));
			$el.parent().parent().addClass('esKit');
			$('#datosVarios').bootstrapTable('resetView');
			console.log(row.porcentaje);
		});
		$tabla.on('editable-shown.bs.table', function(e, field, row, $el, editable){
//			console.log(JSON.stringify(e));
//			console.log(field);
//			console.log(row.codigoProducto);
//			console.log('elemento(td): ' +$el);
//			console.log('elemento(td): ' +JSON.stringify($el));
//			console.log('editable: ' +JSON.stringify(editable));
//			console.log('editable: ' +editable.input);
			$('#datosVarios').bootstrapTable('resetView');
			
		});
		$tabla.on('editable-save.bs.table', function(e, field, row, oldValue, $el){
			$('#datosVarios').bootstrapTable('resetView');
		});

		$tabla.on('editable-hidden.bs.table', function(e, field, row, $el, reason){
			$('#datosVarios').bootstrapTable('resetView');
		});
	   $('#datosVarios').bootstrapTable({
		   		height: 400,
				pagination: true,
				pageSize: 50,
				pageList: [10, 25, 50, 75, 100, 200],
				undefinedText: '',
//				resizable: true,
//				fixed: false,
				classes: 'table table-condensed table-hover',
			columns: [
			{
	           field: 'codigoProducto',
	           title: 'C\u00F3digo',
	           editable: {
                   type: 'text',
                   title: 'C\u00F3digo Producto',
                   showbuttons: false,
                   emptytext: '---',
                   placeholder: 'Ingrese Producto'
               }
	       }, 
	       {
	           field: 'unidad',
	           title: 'UM',
	       },
	       {
	           field: 'descripcion',
	           title: 'Descripcion',
	           width: '500px'
	       },
	       {
	           field: 'cantidad',
	           title: 'Cant.',
	           editable: {
                   type: 'text',
                   title: 'Cantidad',
                   showbuttons: false,
                   emptytext: '---',
                   placeholder: 'Ingrese Cantidad',
                   placement: 'bottom',
                   validate: function(value){
                	   value = $.trim(value);
                	   if(!value){
                		   
                		   return 'La cantidad no puede quedar vacia';
                	   }
                       if (isNaN(value)) {
                           return 'Necesita ingresar un numero';
                       }
                       return '';
                   }
               }
	       },
	       {
	           field: 'disponible',
	           title: 'Disp.'
	       },
	       {
	           field: 'precio',
	           title: 'PU'
	       },
	       {
	           field: 'porcentaje',
	           title: '%'
	       },
	       {
	           field: 'descuento',
	           title: 'Desc.'
	       },
	       {
	           field: 'importe',
	           title: 'Importe'
	       },
	       {
	           field: 'bodega',
	           title: 'BD'
	       },
	       {
	           field: 'envia',
	           title: 'Envia'
	       },
	       {
	           field: 'descuentoMaximo',
	           title: 'DM'
	       },
	       {
	           field: 'observaciones',
	           title: 'Observaciones'
	       },
	       {
	           field: 'esKit',
	           title: 'Kit',
	           visible: false
	       },
	       {
	           field: 'defectuoso',
	           title: 'DF',
	           visible: false
	       }
	       ]
	   });	
	   $tabla.on('post-body.bs.table', function(){
		   $('#datosVarios').bootstrapTable('resetView');
		   
	   });
	   $('#btnAgregar').click(function(){
		   $tabla.bootstrapTable('insertRow', {
	           index: 1,
	           row: {
	               codigoProducto: '',
	               unidad: 'UNI',
	               cantidad: '',
	               descripcion: 'desc',
	               porcentaje: '',
	               descuento: '',
	               descuentoMaximo: '',
	               envia: '',
	               bodega: '08',
	               disponible: '11',
	               precio: '26.54',
	               importe: '0.00',
	               observaciones: 'nada',
	           }
	       });
//		   $('.porcentaje').editable({
//			    type: 'text',
//			    title: 'Ingrese Porcentaje',
//			    emptytext: '--',
//			    showbuttons: false,
//			    placement: 'bottom'
//			});
//		   $('.porcentaje').on('shown', function(e, editable) {
//		   		console.log(e);
//		   		console.log('Indice' + $(this).parent().parent().index());
//		   		$('#datosVarios').bootstrapTable('resetView');
//		   	});
//		   	$('.porcentaje').on('save', function(e, params) {
//
//		           $('#datosVarios').bootstrapTable('resetView');
//		   	});
//		   	$('.porcentaje').on('hidden', function(e, reason) {
//		           $('#datosVarios').bootstrapTable('resetView');
//		           console.log('actual: ' + $(this).val());
//		           console.log('index: ' +  $(this).parent().parent().index());
//		           $(this).closest('tr').next().find('.bodega').editable('show');
//		   	});
//		   	$('.bodega').editable({
//			    type: 'text',
//			    title: 'Ingrese Porcentaje',
//			    emptytext: '--',
//			    showbuttons: false,
//			    placement: 'bottom'
//			});
//		   $('.bodega').on('shown', function(e, editable) {
//		   		console.log(e);
//		   		console.log('Indice' + $(this).parent().parent().index());
//		   		$('#datosVarios').bootstrapTable('resetView');
//		   	});
//		   	$('.bodega').on('save', function(e, params) {
//
//		           $('#datosVarios').bootstrapTable('resetView');
//		   	});
//		   	$('.bodega').on('hidden', function(e, reason) {
//		           $('#datosVarios').bootstrapTable('resetView');
//		   	        $(this).closest('td').next().find('.editable').editable('show');
//		   	});
		   	
//		   var texto; 
//		   texto = 'Fila Nueva Creada';
//		   $.notify({
//				// options
//				message: texto 
//			},{
//				// settings
//				element: 'body',
//				type: 'danger',
//				delay: 1000,
//				spacing: 2,
//				showProgressbar: true,
//				placement: {
//					from: "top",
//					align: "right"
//				},
//				animate: {
//					enter: 'animated fadeInDown',
//					exit: 'animated fadeOutUp'
//				},
//				newest_on_top: true,
//				allow_dismiss: false,
//			});
	   });
		   
		   
   });
   
   function formatoPorcentaje(value,row,index){
	   return '<a href="#" class="porcentaje">'+value+'</a>'
   }
   function formatoBodega(value,row,index){
	   return '<a href="#" class="bodega">'+value+'</a>'
   }
  }(window.jQuery, window, document));

