package pe.elcomercio.pagoefectivosdkjavasample;

import pe.elcomercio.pagoefectivosdk.util.DocumentType;

/**
 * Created by tohure on 16/10/17.
 */

class TypeDocument extends TypeDefault{

    private DocumentType documentType;

    TypeDocument(String name, DocumentType documentType) {
        super(name);
        this.documentType = documentType;
    }

    DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}