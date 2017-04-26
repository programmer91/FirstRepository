var specializations = new Array();

specializations['B.E/B.Tech'] = new Array('C.S.E','I.T','E.E.E','E.C.E','Civil','Mech.','Aero','Others');

specializations['B.Sc'] = new Array('Computers','Maths','Physics','Others');

specializations['B.Com'] = new Array('General','Computers');

specializations['BA'] = new Array('English','Economics','Others');

specializations['BBM'] = new Array('');

// For Configuring Specializations and Degree Options in Select Box

function getSpecializations(form,index) {

    degSel = document.getElementById('degree');
    specializationList = specializations[degSel.value];

    if(specializationList != null) {
        setSpecializations('specialization',specializationList,specializationList);
    }
}

// Update Options Of Given Degree

function setSpecializations(fieldId, newOptions, newValues) {
    selectedField = document.getElementById(fieldId);
    selectedField.options.length = 0;
    for (i=0; i<newOptions.length; i++) {
        selectedField.options[selectedField.length] = new Option(newOptions[i], newValues[i]);
    }
}

var pgSpecializations = new Array();

pgSpecializations['M.E/M.Tech'] = new Array('C.S.E','I.T','E.E.E','E.C.E','Civil','Mech.','Aero','Others');

pgSpecializations['M.C.A'] = new Array('');

pgSpecializations['M.Sc'] = new Array('Computers','Maths','Physics','Others');

pgSpecializations['MBA'] = new Array('HR & Mktg','FIN & Mktg','HR & FIN');

pgSpecializations['MHRM'] = new Array('');

pgSpecializations['PGDBM'] = new Array('');

function getPgSpecializations(form,index) {

    pgSel = document.getElementById('pg');
    pgSpecializationList = pgSpecializations[pgSel.value];

    if(pgSpecializationList != null) {
        setPgSpecializations('pgSpecialization',pgSpecializationList,pgSpecializationList);
    }
}


function setPgSpecializations(fieldId1, newOptions1, newValues1) {
    selectedField1 = document.getElementById(fieldId1);
    selectedField1.options.length = 0;
    for (i=0; i<newOptions1.length; i++) {
        selectedField1.options[selectedField1.length] = new Option(newOptions1[i], newValues1[i]);
    }
}
