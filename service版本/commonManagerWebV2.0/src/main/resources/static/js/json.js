(function($){
	var zkbr = {};


	zkbr.ajax = function(opts){
		var ops = {};
		var status = 200;
		var rsp = null;
		var baseUri = document.baseURI;
		var params = opts.params;

  		
		ops.url= opts.url,
  		ops.data= opts.data,
		ops.type=(opts.type==undefined)?'post':opts.type,
		ops.async=opts.async,
		ops.success=opts.success;
		
		if(opts.dataType!=null){
			ops.dataType = opts.dataType;
		}
 	 
		try{			
			
			ops.success = function(rsp){

				opts.success(rsp);	
			};			
			$.ajax(ops);
		}catch(e){			
			zkbr.alert('提示信息','返回信息解析异常');
			
			return null;
		}		
		
	};
	
	
	zkbr.json = new (function(){
	    var useHasOwn = !!{}.hasOwnProperty;
	
	    // crashes Safari in some instances
	    //var validRE = /^("(\\.|[^"\\\n\r])*?"|[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t])+?$/;
	
	    var pad = function(n) {
	        return n < 10 ? "0" + n : n;
	    };
		/**
		 * 这个方法应该是jquery的扩展
		 * @param {Object} v
		 */
	    var isDate = function(v){
	        return v && typeof v.getFullYear == 'function';
	    };
	
	    var m = {
	        "\b": '\\b',
	        "\t": '\\t',
	        "\n": '\\n',
	        "\f": '\\f',
	        "\r": '\\r',
	        '"' : '\\"',
	        "\\": '\\\\'
	    };
	
	    var encodeString = function(s){
	        if (/["\\\x00-\x1f]/.test(s)) {
	            return '"' + s.replace(/([\x00-\x1f\\"])/g, function(a, b) {
	                var c = m[b];
	                if(c){
	                    return c;
	                }
	                c = b.charCodeAt();
	                return "\\u00" +
	                    Math.floor(c / 16).toString(16) +
	                    (c % 16).toString(16);
	            }) + '"';
	        }
	        return '"' + s + '"';
	    };
	
	    var encodeArray = function(o){
	        var a = ["["], b, i, l = o.length, v;
	            for (i = 0; i < l; i += 1) {
	                v = o[i];
	                switch (typeof v) {
	                    case "undefined":
	                    case "function":
	                    case "unknown":
	                        break;
	                    default:
	                        if (b) {
	                            a.push(',');
	                        }
	                        a.push(v === null ? "null" : $.zkbr.json.encode(v));
	                        b = true;
	                }
	            }
	            a.push("]");
	            return a.join("");
	    };
	
	    this.encodeDate = function(o){
	        return '"' + o.getFullYear() + "-" +
	                pad(o.getMonth() + 1) + "-" +
	                pad(o.getDate()) + "T" +
	                pad(o.getHours()) + ":" +
	                pad(o.getMinutes()) + ":" +
	                pad(o.getSeconds()) + '"';
	    };
	
	    
	    this.encode = function(o){
	        if(typeof o == "undefined" || o === null){
	            return "null";
	        }else if($.isArray(o)){
	            return encodeArray(o);
	        }else if(isDate(o)){
	            return $.zkbr.json.encodeDate(o);
	        }else if(typeof o == "string"){
	            return encodeString(o);
	        }else if(typeof o == "number"){
	            return isFinite(o) ? String(o) : "null";
	        }else if(typeof o == "boolean"){
	            return String(o);
	        }else {
	            var a = ["{"], b, i, v;
	            for (i in o) {
	                if(!useHasOwn || o.hasOwnProperty(i)) {
	                    v = o[i];
	                    switch (typeof v) {
	                    case "undefined":
	                    case "function":
	                    case "unknown":
	                        break;
	                    default:
	                        if(b){
	                            a.push(',');
	                        }
	                        a.push(this.encode(i), ":",
	                                v === null ? "null" : this.encode(v));
	                        b = true;
	                    }
	                }
	            }
	            a.push("}");
	            return a.join("");
	        }
	    };
	
	    
	    this.decode = function(json){
	        return eval("(" + json + ')');
	    };
	})();
	
	zkbr.getUrlParam = function(name, win){
	    
	    var w = window;
	    if (win != null) {
	        w = win;
	    }
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	    var r = w.location.search.substr(1).match(reg);
	    if (r != null) 
	        return decodeURI(r[2]);
	    return null;
	};
    $.extend({
        "zkbr": zkbr
    });

})(jQuery)