-- Inserimento degli utenti di laboratorio
INSERT INTO lab_users (username)
VALUES
    ('mario'),
    ('luca');


-- Inserimento dei documenti di Mario
INSERT INTO lab_documents (title, content, owner_id)
VALUES
    (
        'Contratto',
        'Questo è un documento di prova appartenente a Mario.',
        (SELECT id FROM lab_users WHERE username = 'mario')
    ),
    (
        'Appunti universitari',
        'Questi sono gli appunti universitari di Mario.',
        (SELECT id FROM lab_users WHERE username = 'mario')
    );


-- Inserimento dei documenti di Luca
INSERT INTO lab_documents (title, content, owner_id)
VALUES
    (
        'Documento personale',
        'Questo è un documento di prova appartenente a Luca.',
        (SELECT id FROM lab_users WHERE username = 'luca')
    ),
    (
        'Note private',
        'Queste sono le note private di Luca.',
        (SELECT id FROM lab_users WHERE username = 'luca')
    );