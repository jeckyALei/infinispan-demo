<html>
<head>
<title>Infinispan Cache Query</title>
<!-- <script type="text/javascript"
	src="http://localhost:8281/infinispan-ws.js"></script> -->
<script type="text/javascript" src="js/infinispan-ws.js"></script>
<script type="text/javascript">
	//获取缓存
	var cache = new Cache("default", "ws://localhost:8281");
	//注册get操作的回调函数（get为异步操作）
	cache.registerCallback(cacheCallback);
	//回调函数的实现
	function cacheCallback(key, value) {
		var node = document.getElementById("val");
		node.value = value;
		alert("SECCESS: " + value);
	}
	//put操作
	function op_put(k, v) {
		cache.put(k, v);
		alert("PUT SECCESS");
	}
	//put操作
	function op_remove(k) {
		cache.remove(k)
		alert("REMOVE SECCESS");
	}
	//get操作，成功后触发回调函数cacheCallback
	function op_get(k) {
		cache.get(k);
	}
</script>
</head>
<body>
	<form onsubmit="return false;">
		Key: <input type="text" id="key" /> Value: <input type="text"
			id="val" /><br /> <input type="button" value="Put"
			onclick="op_put(this.form.key.value, this.form.val.value)" /> <input
			type="button" value="Get" onclick="op_get(this.form.key.value)" /> <input
			type="button" value="Remove" onclick="op_remove(this.form.key.value)" />
	</form>
</body>
</html>