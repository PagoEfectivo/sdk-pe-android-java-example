package pe.elcomercio.pagoefectivosdkjavasample.model;

import pe.elcomercio.pagoefectivosdk.util.DocumentType;

/**
 * Created by tohure on 16/10/17.
 */

public class TypeDocumentEntity extends TypeDefaultEntity {

    private DocumentType documentType;

    public TypeDocumentEntity(String name, DocumentType documentType) {
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