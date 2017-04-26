//Pulsating Text (Chris A e-mail: KilerCris@Mail.com)
//Permission granted to Dynamic Drive to feature script in archive
//For full source and 100's more DHTML scripts, visit http://www.dynamicdrive.com

var divs = new Array();
var da = document.all;
var start;

//CONFIGUER THESE VARS!!!!!!
//speed of pulsing
var speed = 50;

function initVars(){

if (!document.all)
return

//Extend of shrink the below list all you want
//put an "addDiv(1,"2",3,4); for each div you made, 
//1)'id' of div
//2)color or glow(name or hex)(in quotes!!!)
//3)minimum strength
//4)maximum strength

addDiv(hi,"#3DB1FA",1,70);
addDiv(welcome,"#3DB1FA",3,70);
addDiv(message,"#3DB1FA",1,70);
addDiv(msg2,"#3DB1FA",1,70);
addDiv(msg3,"#3DB1FA",1,70);
addDiv(msg4,"#3DB1FA",1,70);

//NO MORE EDITING!!!!!!



startGlow();
}

function addDiv(id,color,min,max)
{
var j = divs.length;
divs[j] = new Array(5);
divs[j][0] = id;
divs[j][1] = color;
divs[j][2] = min;
divs[j][3] = max;
divs[j][4] = true;
}

function startGlow()
{
	if (!document.all)
		return 0;

	for(var i=0;i<divs.length;i++)
	{
		divs[i][0].style.filter = "Glow(Color=" + divs[i][1] + ", Strength=" + divs[i][2] + ")";
		divs[i][0].style.width = "100%";
	}

	start = setInterval('update()',speed);
}

function update()
{
	for (var i=0;i<divs.length;i++)
	{
		if (divs[i][4])
		{
			divs[i][0].filters.Glow.Strength++;
			if (divs[i][0].filters.Glow.Strength == divs[i][3])
				divs[i][4] = false;
		}
	
		if (!divs[i][4])
		{
			divs[i][0].filters.Glow.Strength--;
			if (divs[i][0].filters.Glow.Strength == divs[i][2])
				divs[i][4] = true;
		}
	}
}