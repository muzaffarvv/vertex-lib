// VertexLib — api.js
// Mock data bilan ishlaydi. Backend tayyor bo'lganda USE_MOCK = false qiling.

const USE_MOCK = true;

// ─── Mock Ma'lumotlar ─────────────────────────────────────────────────────────

const MOCK_DATA = {

    genres: [
        { id: '1',  name: 'Action',    description: 'Jangovar va qiziqarli filmlar' },
        { id: '2',  name: 'Drama',     description: 'Hayotiy va hissiyotli filmlar' },
        { id: '3',  name: 'Sci-Fi',    description: 'Ilmiy-fantastik filmlar' },
        { id: '4',  name: 'Thriller',  description: 'Taranglashtiradigan filmlar' },
        { id: '5',  name: 'Comedy',    description: 'Kulgili va quvnoq filmlar' },
        { id: '6',  name: 'Crime',     description: 'Jinoyat va detektiv filmlar' },
        { id: '7',  name: 'Horror',    description: "Qo'rqinchli filmlar" },
        { id: '8',  name: 'Romance',   description: 'Muhabbat haqidagi filmlar' },
        { id: '9',  name: 'Animation', description: 'Multfilm va animatsiya' },
        { id: '10', name: 'Biography', description: 'Haqiqiy hayot hikoyalari' },
        { id: '11', name: 'History',   description: 'Tarixiy filmlar' },
        { id: '12', name: 'Fantasy',   description: 'Sehrli dunyo filmlari' },
    ],

    movies: [
        { id: 1,  title: 'Inception',                director: 'Christopher Nolan',  isbn: '978-0-00-001-1', genre: { id: '3', name: 'Sci-Fi' },    publishedYear: 2010, totalCopies: 5, availableCopies: 3, author: 'Christopher Nolan'  },
        { id: 2,  title: 'The Dark Knight',          director: 'Christopher Nolan',  isbn: '978-0-00-002-8', genre: { id: '1', name: 'Action' },    publishedYear: 2008, totalCopies: 4, availableCopies: 1, author: 'Christopher Nolan'  },
        { id: 3,  title: 'Interstellar',             director: 'Christopher Nolan',  isbn: '978-0-00-003-5', genre: { id: '3', name: 'Sci-Fi' },    publishedYear: 2014, totalCopies: 6, availableCopies: 4, author: 'Christopher Nolan'  },
        { id: 4,  title: 'Parasite',                 director: 'Bong Joon-ho',       isbn: '978-0-00-004-2', genre: { id: '4', name: 'Thriller' },  publishedYear: 2019, totalCopies: 3, availableCopies: 0, author: 'Bong Joon-ho'       },
        { id: 5,  title: 'The Godfather',            director: 'Francis F. Coppola', isbn: '978-0-00-005-9', genre: { id: '6', name: 'Crime' },     publishedYear: 1972, totalCopies: 2, availableCopies: 2, author: 'Francis F. Coppola' },
        { id: 6,  title: 'Pulp Fiction',             director: 'Quentin Tarantino',  isbn: '978-0-00-006-6', genre: { id: '6', name: 'Crime' },     publishedYear: 1994, totalCopies: 3, availableCopies: 1, author: 'Quentin Tarantino' },
        { id: 7,  title: 'The Shawshank Redemption', director: 'Frank Darabont',     isbn: '978-0-00-007-3', genre: { id: '2', name: 'Drama' },     publishedYear: 1994, totalCopies: 4, availableCopies: 4, author: 'Frank Darabont'    },
        { id: 8,  title: 'Forrest Gump',             director: 'Robert Zemeckis',    isbn: '978-0-00-008-0', genre: { id: '2', name: 'Drama' },     publishedYear: 1994, totalCopies: 5, availableCopies: 3, author: 'Robert Zemeckis'   },
        { id: 9,  title: 'The Matrix',               director: 'Wachowski Sisters',  isbn: '978-0-00-009-7', genre: { id: '3', name: 'Sci-Fi' },    publishedYear: 1999, totalCopies: 4, availableCopies: 0, author: 'Wachowski Sisters' },
        { id: 10, title: 'Gladiator',                director: 'Ridley Scott',       isbn: '978-0-00-010-3', genre: { id: '1', name: 'Action' },    publishedYear: 2000, totalCopies: 3, availableCopies: 2, author: 'Ridley Scott'      },
        { id: 11, title: "Schindler's List",         director: 'Steven Spielberg',   isbn: '978-0-00-011-0', genre: { id: '2', name: 'Drama' },     publishedYear: 1993, totalCopies: 3, availableCopies: 3, author: 'Steven Spielberg'  },
        { id: 12, title: 'The Lion King',            director: 'Roger Allers',       isbn: '978-0-00-012-7', genre: { id: '9', name: 'Animation' }, publishedYear: 1994, totalCopies: 6, availableCopies: 5, author: 'Roger Allers'      },
    ],

    users: [
        { id: 1,  fullName: 'Admin Adminov',     phoneNumber: '998901234567', role: 'ADMIN',  createdAt: '2024-01-01' },
        { id: 2,  fullName: 'Jasur Toshmatov',   phoneNumber: '998901234568', role: 'MEMBER', createdAt: '2024-01-12' },
        { id: 3,  fullName: 'Malika Yusupova',   phoneNumber: '998901234569', role: 'STAFF',  createdAt: '2024-01-18' },
        { id: 4,  fullName: 'Bobur Rahimov',     phoneNumber: '998901234570', role: 'MEMBER', createdAt: '2024-02-03' },
        { id: 5,  fullName: 'Zulfiya Karimova',  phoneNumber: '998901234571', role: 'MEMBER', createdAt: '2024-02-20' },
        { id: 6,  fullName: 'Sherzod Mirzayev',  phoneNumber: '998901234572', role: 'MEMBER', createdAt: '2024-03-01' },
        { id: 7,  fullName: 'Nilufar Qosimova',  phoneNumber: '998901234573', role: 'MEMBER', createdAt: '2024-03-14' },
        { id: 8,  fullName: 'Umid Xasanov',      phoneNumber: '998901234574', role: 'STAFF',  createdAt: '2024-04-05' },
        { id: 9,  fullName: 'Dildora Nazarova',  phoneNumber: '998901234575', role: 'MEMBER', createdAt: '2024-04-20' },
        { id: 10, fullName: 'Otabek Sultanov',   phoneNumber: '998901234576', role: 'MEMBER', createdAt: '2024-05-01' },
    ],

    get loans() {
        const now = new Date();
        const d = (days) => new Date(now.getTime() + days * 86400000).toISOString();
        return [
            { id: '1', movie: { title: 'Inception' },                member: { fullName: 'Jasur Toshmatov' },  staff: { fullName: 'Malika Yusupova' }, loanDate: d(-20), dueDate: d(-5),  returnDate: null,  fineAmount: 15000 },
            { id: '2', movie: { title: 'Interstellar' },             member: { fullName: 'Nilufar Qosimova' }, staff: { fullName: 'Umid Xasanov' },    loanDate: d(-10), dueDate: d(4),   returnDate: null,  fineAmount: null  },
            { id: '3', movie: { title: 'Parasite' },                 member: { fullName: 'Bobur Rahimov' },    staff: { fullName: 'Malika Yusupova' }, loanDate: d(-30), dueDate: d(-15), returnDate: d(-2), fineAmount: null  },
            { id: '4', movie: { title: 'The Dark Knight' },          member: { fullName: 'Dildora Nazarova' }, staff: { fullName: 'Umid Xasanov' },    loanDate: d(-7),  dueDate: d(7),   returnDate: null,  fineAmount: null  },
            { id: '5', movie: { title: 'The Matrix' },               member: { fullName: 'Sherzod Mirzayev' }, staff: { fullName: 'Malika Yusupova' }, loanDate: d(-45), dueDate: d(-30), returnDate: d(-25),fineAmount: 25000 },
            { id: '6', movie: { title: 'Pulp Fiction' },             member: { fullName: 'Zulfiya Karimova' }, staff: { fullName: 'Umid Xasanov' },    loanDate: d(-3),  dueDate: d(11),  returnDate: null,  fineAmount: null  },
            { id: '7', movie: { title: 'The Shawshank Redemption' }, member: { fullName: 'Otabek Sultanov' },  staff: { fullName: 'Malika Yusupova' }, loanDate: d(-60), dueDate: d(-45), returnDate: d(-40),fineAmount: null  },
            { id: '8', movie: { title: 'Gladiator' },                member: { fullName: 'Jasur Toshmatov' },  staff: { fullName: 'Umid Xasanov' },    loanDate: d(-5),  dueDate: d(-1),  returnDate: null,  fineAmount: 5000  },
        ];
    },
};

// currentUser — barcha sahifalar bunga murojaat qiladi
const currentUser = {
    id: 1,
    fullName: 'Admin Adminov',
    username: 'admin',
    role: 'ADMIN',
};

// ─── Pageable wrapper ─────────────────────────────────────────────────────────

function _pageable(list, page, size) {
    return {
        content: list.slice(page * size, page * size + size),
        totalElements: list.length,
        totalPages: Math.ceil(list.length / size) || 1,
        number: page,
        size,
    };
}

// ─── Fake Response (res.ok / res.json() uslubi uchun) ────────────────────────

function _fakeRes(data, status = 200) {
    return {
        ok: status >= 200 && status < 300,
        status,
        json: async () => data,
        text: async () => JSON.stringify(data),
    };
}

// ─── Mock Router ──────────────────────────────────────────────────────────────

function _mock(method, path, body) {
    const url   = new URL(path, 'http://localhost');
    const p     = url.pathname;
    const sp    = url.searchParams;
    const page  = parseInt(sp.get('page') || '0');
    const size  = parseInt(sp.get('size') || '20');
    const sort  = sp.get('sort') || '';
    const role  = sp.get('role') || '';

    // GENRES
    if (method === 'GET'  && p === '/api/v1/genres') return _pageable([...MOCK_DATA.genres], page, size);
    if (method === 'POST' && p === '/api/v1/genres') {
        const g = { id: String(Date.now()), ...body };
        MOCK_DATA.genres.push(g);
        return g;
    }
    const gm = p.match(/^\/api\/v1\/genres\/([^/]+)$/);
    if (gm) {
        if (method === 'PUT') {
            const i = MOCK_DATA.genres.findIndex(x => x.id === gm[1]);
            if (i >= 0) MOCK_DATA.genres[i] = { ...MOCK_DATA.genres[i], ...body };
            return MOCK_DATA.genres[i] || {};
        }
        if (method === 'DELETE') {
            const i = MOCK_DATA.genres.findIndex(x => x.id === gm[1]);
            if (i >= 0) MOCK_DATA.genres.splice(i, 1);
            return {};
        }
        return MOCK_DATA.genres.find(x => x.id === gm[1]) || null;
    }

    // MOVIES
    if (method === 'GET'  && p === '/api/v1/movies') {
        let list = [...MOCK_DATA.movies];
        if (sort.includes('desc')) list.reverse();
        return _pageable(list, page, size);
    }
    if (method === 'POST' && p === '/api/v1/movies') {
        const genreObj = MOCK_DATA.genres.find(g => g.id === String(body.genreId)) || null;
        const m = { id: Date.now(), ...body, genre: genreObj, availableCopies: body.totalCopies || 0 };
        MOCK_DATA.movies.push(m);
        return m;
    }
    const mm = p.match(/^\/api\/v1\/movies\/(\d+)(?:\/details)?$/);
    if (mm) {
        const mid = parseInt(mm[1]);
        if (method === 'GET') return MOCK_DATA.movies.find(x => x.id === mid) || null;
        if (method === 'PUT' || method === 'PATCH') {
            const i = MOCK_DATA.movies.findIndex(x => x.id === mid);
            if (i >= 0) {
                if (body.genreId) body.genre = MOCK_DATA.genres.find(g => g.id === String(body.genreId)) || MOCK_DATA.movies[i].genre;
                MOCK_DATA.movies[i] = { ...MOCK_DATA.movies[i], ...body };
            }
            return MOCK_DATA.movies[i] || null;
        }
        if (method === 'DELETE') {
            const i = MOCK_DATA.movies.findIndex(x => x.id === mid);
            if (i >= 0) MOCK_DATA.movies.splice(i, 1);
            return {};
        }
    }

    // USERS
    if (method === 'GET'  && p === '/api/v1/users') {
        let list = [...MOCK_DATA.users];
        if (role) list = list.filter(u => u.role === role);
        if (sort.includes('desc')) list.reverse();
        return _pageable(list, page, size);
    }
    if (method === 'POST' && p === '/api/v1/users') {
        const u = { id: Date.now(), createdAt: new Date().toISOString().slice(0, 10), ...body };
        MOCK_DATA.users.push(u);
        return u;
    }
    const um = p.match(/^\/api\/v1\/users\/(\d+)$/);
    if (um) {
        const uid = parseInt(um[1]);
        if (method === 'GET') return MOCK_DATA.users.find(x => x.id === uid) || null;
        if (method === 'PUT') {
            const i = MOCK_DATA.users.findIndex(x => x.id === uid);
            if (i >= 0) MOCK_DATA.users[i] = { ...MOCK_DATA.users[i], ...body };
            return MOCK_DATA.users[i] || null;
        }
        if (method === 'DELETE') {
            const i = MOCK_DATA.users.findIndex(x => x.id === uid);
            if (i >= 0) MOCK_DATA.users.splice(i, 1);
            return {};
        }
    }

    // LOANS
    const _loans = MOCK_DATA.loans; // getter — har safar yangi array
    if (method === 'GET'  && p === '/api/v1/loans') return _pageable([..._loans], page, size);
    if (method === 'POST' && p === '/api/v1/loans') {
        const movie  = MOCK_DATA.movies.find(m => m.id === parseInt(body.movieId));
        const member = MOCK_DATA.users.find(u => u.id === parseInt(body.memberId));
        const staff  = MOCK_DATA.users.find(u => u.id === parseInt(body.staffId));
        return {
            id: String(Date.now()),
            movie:  { title: movie?.title           || '—' },
            member: { fullName: member?.fullName    || '—' },
            staff:  { fullName: staff?.fullName     || '—' },
            loanDate: new Date().toISOString(),
            dueDate: body.dueDate || new Date(Date.now() + 14 * 86400000).toISOString(),
            returnDate: null,
            fineAmount: null,
        };
    }
    const lm = p.match(/^\/api\/v1\/loans\/([^/]+)(?:\/return)?$/);
    if (lm) {
        if (method === 'GET') return _loans.find(x => x.id === lm[1]) || null;
        if (method === 'PUT' || method === 'PATCH') return { id: lm[1], ...body, returnDate: body.returnDate || new Date().toISOString() };
    }

    console.warn('[api.js mock] Noma\'lum endpoint:', method, p);
    return null;
}

// ─── Delay ────────────────────────────────────────────────────────────────────

const _delay = (ms = 120) => new Promise(r => setTimeout(r, ms));

// ─── API — ikkala call uslubini qo'llab-quvvatlaydi ──────────────────────────
//
//  Uslub 1 — genres.html va loans.html ishlatadi:
//    const res = await api('GET', '/api/v1/genres')
//    const data = await res.json()          res.ok ni tekshiradi
//
//  Uslub 2 — movies.html va users.html ishlatadi:
//    const res = await api('GET', '/api/v1/movies')
//    res.ok, res.json() — xuddi fetch kabi
//
//  Uslub 3 — dashboard.html ishlatadi:
//    const data = await api.get('/api/v1/movies')   → to'g'ridan-to'g'ri data

async function api(method, path, body) {
    await _delay();
    if (USE_MOCK) {
        const data = _mock(method.toUpperCase(), path, body || {});
        return _fakeRes(data, data === null ? 404 : 200);
    }
    const token = localStorage.getItem('token');
    const opts = {
        method,
        headers: {
            'Content-Type': 'application/json',
            ...(token ? { Authorization: `Bearer ${token}` } : {}),
        },
    };
    if (body && method !== 'GET') opts.body = JSON.stringify(body);
    return fetch(path, opts);
}

api.get = async function(path) {
    await _delay();
    if (USE_MOCK) {
        const data = _mock('GET', path, {});
        if (data === null) throw new Error('Not found: ' + path);
        return data;
    }
    const token = localStorage.getItem('token');
    const res = await fetch(path, { headers: { ...(token ? { Authorization: `Bearer ${token}` } : {}) } });
    if (!res.ok) throw new Error(`GET ${path} — ${res.status}`);
    return res.json();
};

api.post = async function(path, body = {}) {
    await _delay(150);
    if (USE_MOCK) return _mock('POST', path, body);
    const token = localStorage.getItem('token');
    const res = await fetch(path, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', ...(token ? { Authorization: `Bearer ${token}` } : {}) },
        body: JSON.stringify(body),
    });
    if (!res.ok) throw new Error(`POST ${path} — ${res.status}`);
    return res.json();
};

api.put = async function(path, body = {}) {
    await _delay(150);
    if (USE_MOCK) return _mock('PUT', path, body);
    const token = localStorage.getItem('token');
    const res = await fetch(path, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json', ...(token ? { Authorization: `Bearer ${token}` } : {}) },
        body: JSON.stringify(body),
    });
    if (!res.ok) throw new Error(`PUT ${path} — ${res.status}`);
    return res.json();
};

api.delete = async function(path) {
    await _delay(100);
    if (USE_MOCK) return _mock('DELETE', path, {});
    const token = localStorage.getItem('token');
    const res = await fetch(path, {
        method: 'DELETE',
        headers: { ...(token ? { Authorization: `Bearer ${token}` } : {}) },
    });
    if (!res.ok) throw new Error(`DELETE ${path} — ${res.status}`);
    return res.status === 204 ? null : res.json();
};

api.logout = function() {
    localStorage.removeItem('token');
    window.location.href = '/login';
};