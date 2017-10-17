package pe.elcomercio.pagoefectivosdkjavasample.data;

import pe.elcomercio.pagoefectivosdk.util.DocumentType;

/**
 * Created by tohure on 16/10/17.
 */

public class TypeDocument extends TypeDefault{

    private DocumentType documentType;

    public TypeDocument(String name, DocumentType documentType) {
        super(name);
        this.documentType = documentType;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}