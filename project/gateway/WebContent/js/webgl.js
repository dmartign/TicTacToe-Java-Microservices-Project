//Note: WebGL basic code and ideas was obtained from tutorials, 
//especially http://learningwebgl.com/blog/?p=28
// shaders are declared in home.jsp and initialized here. All the rendering takes place here

	var mouse = { x: 0, y: 0 , clicked: false};
	var renderWIDTH = 600;
	var renderHEIGHT = 600;
	var xCoord;
	var yCoord;
	
	document.addEventListener('mousedown', onDocumentMouseDown, false);
	
	function onDocumentMouseDown(event) {
	    function getMousePos(canvas, evt) {
          var rect = canvas.getBoundingClientRect();
          return {
            x: evt.clientX - rect.left,
            y: evt.clientY - rect.top
          };
        } 
        var canvas = document.getElementById('tictactoe-canvas');
        var context = canvas.getContext('2d');
	    var mousePos = getMousePos(canvas, event);
		mouse.x = mousePos.x;
		mouse.y = mousePos.y;
		xCoord = parseInt(mouse.x/(renderWIDTH/3));
		yCoord = parseInt(mouse.y/(renderHEIGHT/3));
		if(xCoord <= 2 && xCoord >= 0 && yCoord <= 2 && yCoord >= 0){
			mouse.clicked = true;
			gameClick(yCoord, xCoord);
		}
	}
	
	
    var gl;
    function initGL(canvas) {
        try {
            gl = canvas.getContext("experimental-webgl");
            gl.viewportWidth = canvas.width;
            gl.viewportHeight = canvas.height;
        } catch (e) {
        }
        if (!gl) {
            alert("Could not initialise WebGL, sorry :-(");
        }
    }

    function getShader(gl, id) {
        var shaderScript = document.getElementById(id);
        if (!shaderScript) {
            return null;
        }

        var str = "";
        var k = shaderScript.firstChild;
        while (k) {
            if (k.nodeType == 3) {
                str += k.textContent;
            }
            k = k.nextSibling;
        }

        var shader;
        if (shaderScript.type == "x-shader/x-fragment") {
            shader = gl.createShader(gl.FRAGMENT_SHADER);
        } else if (shaderScript.type == "x-shader/x-vertex") {
            shader = gl.createShader(gl.VERTEX_SHADER);
        } else {
            return null;
        }

        gl.shaderSource(shader, str);
        gl.compileShader(shader);

        if (!gl.getShaderParameter(shader, gl.COMPILE_STATUS)) {
            alert(gl.getShaderInfoLog(shader));
            return null;
        }

        return shader;
    }

    
    var shaderProgram;
    function initShaders() {
        var fragmentShader = getShader(gl, "shader-fs");		
        var vertexShader = getShader(gl, "shader-vs");

        shaderProgram = gl.createProgram();
        gl.attachShader(shaderProgram, vertexShader);
        gl.attachShader(shaderProgram, fragmentShader);
        gl.linkProgram(shaderProgram);
		
        if (!gl.getProgramParameter(shaderProgram, gl.LINK_STATUS)) {
            alert("Could not initialise shaders");
        }

		gl.useProgram(shaderProgram);
		
        shaderProgram.vertexPositionAttribute = gl.getAttribLocation(shaderProgram, "aVertexPosition");
        gl.enableVertexAttribArray(shaderProgram.vertexPositionAttribute);
		
        shaderProgram.pMatrixUniform = gl.getUniformLocation(shaderProgram, "uPMatrix");
        shaderProgram.mvMatrixUniform = gl.getUniformLocation(shaderProgram, "uMVMatrix");	
    }

    
    var mvMatrix = mat4.create();
    var pMatrix = mat4.create();
    function setMatrixUniforms() {
        gl.uniformMatrix4fv(shaderProgram.pMatrixUniform, false, pMatrix);
        gl.uniformMatrix4fv(shaderProgram.mvMatrixUniform, false, mvMatrix);
    }

	
    var triangleVertexPositionBuffer;
	var triangleVertexColorBuffer;
	var squareVertexPositionBuffer;
	var squareVertexColorBuffer;
	var linePositionBuffer;
	var linePositionBuffer2;

    function initBuffers() {
        triangleVertexPositionBuffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexPositionBuffer);
        var vertices = [
             0.0,  1.0,  0.0,
            -1.0, -1.0,  0.0,
             1.0, -1.0,  0.0
        ];
        gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
        triangleVertexPositionBuffer.itemSize = 3;
        triangleVertexPositionBuffer.numItems = 3;

        squareVertexPositionBuffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexPositionBuffer);
        vertices = [
             1.0,  1.0,  0.0,
            -1.0,  1.0,  0.0,
             1.0, -1.0,  0.0,
            -1.0, -1.0,  0.0
        ];
        gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
        squareVertexPositionBuffer.itemSize = 3;
        squareVertexPositionBuffer.numItems = 4;
		
        // straight horizontal line
		linePositionBuffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, linePositionBuffer);
        vertices = [
			20.0, 0.0, 0.0,
			-20.0, 0.0, 0.0
        ];
        gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
        linePositionBuffer.itemSize = 3;
        linePositionBuffer.numItems = 2;
		
		// straight vertical line
		linePositionBuffer2 = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, linePositionBuffer2);
        vertices = [
			0.0, 20.0, 0.0,
			0.0, -20.0, 0.0
        ];
        gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
        linePositionBuffer2.itemSize = 3;
        linePositionBuffer2.numItems = 2;
    }

	function drawVerticalLine(x,y,z){
		mat4.identity(mvMatrix);
		mat4.translate(mvMatrix, [x, y, z]);
        gl.bindBuffer(gl.ARRAY_BUFFER, linePositionBuffer2);
        gl.vertexAttribPointer(shaderProgram.vertexPositionAttribute, linePositionBuffer2.itemSize, gl.FLOAT, false, 0, 0);
        setMatrixUniforms();
        gl.drawArrays(gl.LINE_LOOP, 0, linePositionBuffer2.numItems);
	}
	
	function drawHorizontalLine(x,y,z){
		mat4.identity(mvMatrix);
		mat4.translate(mvMatrix, [x,y,z]);
        gl.bindBuffer(gl.ARRAY_BUFFER, linePositionBuffer);
        gl.vertexAttribPointer(shaderProgram.vertexPositionAttribute, linePositionBuffer.itemSize, gl.FLOAT, false, 0, 0);
        setMatrixUniforms();
        gl.drawArrays(gl.LINE_LOOP, 0, linePositionBuffer.numItems);
	}
	
	
	function drawGrid() {
		// vertical lines
		drawVerticalLine(1.36, 0.0, -10);
		drawVerticalLine(-1.37, 0.0, -10);
		// horizontal lines
		drawHorizontalLine(0.0, 1.37, -10.0);
		drawHorizontalLine(0.0, -1.36, -10.0);
	}	
	
	function drawTriangle(x, y, z) {
		mat4.identity(mvMatrix);
		mat4.translate(mvMatrix, [x, y, z]);
        gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexPositionBuffer);
        gl.vertexAttribPointer(shaderProgram.vertexPositionAttribute, triangleVertexPositionBuffer.itemSize, gl.FLOAT, false, 0, 0);
		setMatrixUniforms();
        gl.drawArrays(gl.TRIANGLES, 0, triangleVertexPositionBuffer.numItems);
	}
	
	function drawSquare(x, y, z) {
		mat4.identity(mvMatrix);
		mat4.translate(mvMatrix, [x, y, z]);
        gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexPositionBuffer);
        gl.vertexAttribPointer(shaderProgram.vertexPositionAttribute, squareVertexPositionBuffer.itemSize, gl.FLOAT, false, 0, 0);
		setMatrixUniforms();
        gl.drawArrays(gl.TRIANGLE_STRIP, 0, squareVertexPositionBuffer.numItems);
		
	}

	
	function drawGame(game) {
  	drawScene();
	for(var i = 0; i < game.length; i++) {
		for(var j = 0; j < game[i].length; j++) {
			x = -3.5+j*3.3
			y = 3.5-i*3.3
			z = -12
			if(game[i][j] == 'X') {
				drawTriangle(x,y,z);
			}
			else if(game[i][j] == 'O'){ 
				drawSquare(x,y,z);
			}
		}
	}
	}
	
    function drawScene() {
        gl.viewport(0, 0, gl.viewportWidth, gl.viewportHeight);
        gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

        mat4.perspective(45, gl.viewportWidth / gl.viewportHeight, 0.1, 100.0, pMatrix);

        mat4.identity(mvMatrix);

        // initial perspective to draw grid
		mat4.translate(mvMatrix, [0.0, 0.0, -10.0]);
		drawGrid();
		mat4.translate(mvMatrix, [-3.4, 3.5, -10.0]); // positions first shape at the top-left position
	}

    function webGLStart() {
        var canvas = document.getElementById("tictactoe-canvas");
        initGL(canvas);
        initShaders();
        initBuffers();

        gl.clearColor(0.0, 0.0, 0.0, 0.3);
        gl.enable(gl.DEPTH_TEST);

        drawScene();
    }