// This is a javascript file



function myPopupRelocate(popupDiv) {
  var scrolledX, scrolledY;
  if( self.pageYOffset ) {
    scrolledX = self.pageXOffset;
    scrolledY = self.pageYOffset;
  } else if( document.documentElement && document.documentElement.scrollTop ) {
    scrolledX = document.documentElement.scrollLeft;
    scrolledY = document.documentElement.scrollTop;
  } else if( document.body ) {
    scrolledX = document.body.scrollLeft;
    scrolledY = document.body.scrollTop;
  }

  var centerX, centerY;
  if( self.innerHeight ) {
    centerX = self.innerWidth;
    centerY = self.innerHeight;
  } else if( document.documentElement && document.documentElement.clientHeight ) {
    centerX = document.documentElement.clientWidth;
    centerY = document.documentElement.clientHeight;
  } else if( document.body ) {
    centerX = document.body.clientWidth;
    centerY = document.body.clientHeight;
  }
  var leftOffset = scrolledX + (centerX - 608) / 2;
  var topOffset = scrolledY + (centerY - 400) / 2;
  document.getElementById(popupDiv).style.top = topOffset + "px";
  document.getElementById(popupDiv).style.left = leftOffset + "px";
}

function fireMyPopup(popupDiv) {
  myPopupRelocate(popupDiv);
  //document.getElementById(popupDiv).style.display = "block";
  //document.body.onscroll = myPopupRelocate;
  //window.onscroll = myPopupRelocate;
}