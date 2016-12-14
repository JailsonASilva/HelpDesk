function closeDialogIfSucess(xhr, status, args, dialogWidget, dialogo) {
    if (args.validationFailed || args.KEEP_DIALOG_OPENED) {
        jQuery('#'+dialogo).effect("bounce", {times : 4, distance : 20}, 100);
    } 
    else {
    dialogWidget.hide();
    }
}