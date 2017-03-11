define({ "api": [
  {
    "type": "post",
    "url": "http://54.169.225.125:3000/login",
    "title": "login",
    "name": "login",
    "permission": [
      {
        "name": "public"
      }
    ],
    "group": "Authentication",
    "description": "<p>login</p>",
    "examples": [
      {
        "title": "Example",
        "content": "http://54.169.225.125:3000/login",
        "type": "json"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>username</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>password</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Bool",
            "optional": false,
            "field": "success",
            "description": "<p>true</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>username</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "app/controller/authController.js",
    "groupTitle": "Authentication"
  },
  {
    "type": "post",
    "url": "http://54.169.225.125:3000/register",
    "title": "register new account",
    "name": "register_new_account",
    "permission": [
      {
        "name": "public"
      }
    ],
    "group": "Authentication",
    "description": "<p>register new account</p>",
    "examples": [
      {
        "title": "Example",
        "content": "http://54.169.225.125:3000/register",
        "type": "json"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>username</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>password</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "age",
            "description": "<p>age</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>name</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>successful</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "app/controller/authController.js",
    "groupTitle": "Authentication"
  },
  {
    "type": "get",
    "url": "http://54.169.225.125:3000/",
    "title": "Server checker",
    "name": "CheckServer",
    "permission": [
      {
        "name": "public"
      }
    ],
    "group": "Common",
    "description": "<p>Check server is running!!!</p>",
    "examples": [
      {
        "title": "Example",
        "content": "http://54.169.225.125:3000/",
        "type": "json"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "200",
            "description": ""
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "app/controller/apiController.js",
    "groupTitle": "Common"
  },
  {
    "type": "post",
    "url": "http://54.169.225.125:3000/api/log",
    "title": "log user's behavior",
    "name": "log_user_s_behavior",
    "permission": [
      {
        "name": "private"
      }
    ],
    "group": "Log",
    "description": "<p>log user's answer every questions!!!</p>",
    "examples": [
      {
        "title": "Example",
        "content": "http://54.169.225.125:3000/api/log",
        "type": "json"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "ObjectId",
            "optional": false,
            "field": "taskId",
            "description": "<p>id of question</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "category",
            "description": "<p>category</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "attempts",
            "description": "<p>number of attemptions after a successful answer</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>username</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>successful</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "app/controller/apiController.js",
    "groupTitle": "Log"
  },
  {
    "type": "get",
    "url": "http://54.169.225.125:3000/api/log",
    "title": "exports user behavior data",
    "name": "export_user_behavior_data",
    "permission": [
      {
        "name": "private"
      }
    ],
    "group": "User",
    "description": "<p>export data of user!!!</p>",
    "examples": [
      {
        "title": "Example",
        "content": "http://54.169.225.125:3000/api/log/meodorewan/comparision\nhttp://54.169.225.125:3000/api/log/meodorewan/progression",
        "type": "json"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "object",
            "description": "<p>tập đếm, nhận biết...</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "object.attempts",
            "description": "<p>the total number of attemptions before correct answers</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "object.createdAt",
            "description": "<p>timeline</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "app/controller/apiController.js",
    "groupTitle": "User"
  },
  {
    "type": "get",
    "url": "http://54.169.225.125:3000/api/user/:username",
    "title": "get User Info",
    "name": "getUserInfo",
    "permission": [
      {
        "name": "public"
      }
    ],
    "group": "User",
    "description": "<p>Get all information of an user!!!</p>",
    "examples": [
      {
        "title": "Example",
        "content": "http://54.169.225.125:3000/api/user/meodorewan",
        "type": "json"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "ObjectId",
            "optional": false,
            "field": "_id",
            "description": "<p>id of user</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>username</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "info",
            "description": "<p>info of user</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "info.age",
            "description": "<p>age of user</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "info.name",
            "description": "<p>name of user</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "app/controller/apiController.js",
    "groupTitle": "User"
  },
  {
    "type": "get",
    "url": "http://54.169.225.125:3000/api/questions/:username/:number",
    "title": "get questions",
    "name": "get_questions",
    "permission": [
      {
        "name": "public"
      }
    ],
    "group": "User",
    "description": "<p>Get a number of questions!!!</p>",
    "examples": [
      {
        "title": "Example",
        "content": "http://54.169.225.125:3000/api/questions/meodorewan/10",
        "type": "json"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "ObjectId",
            "optional": false,
            "field": "_id",
            "description": "<p>id of question</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "category",
            "description": "<p>category of question</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "level",
            "description": "<p>level of question (automatically fit with current user's level)</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "question",
            "description": "<p>detail of question</p>"
          },
          {
            "group": "Success 200",
            "type": "[url]",
            "optional": false,
            "field": "question.image",
            "description": "<p>images</p>"
          },
          {
            "group": "Success 200",
            "type": "[url]",
            "optional": false,
            "field": "question.audio",
            "description": "<p>audio</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "question.text",
            "description": "<p>text</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "question.answer",
            "description": "<p>answer</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "horizontal",
            "description": "<p>sharp</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "app/controller/apiController.js",
    "groupTitle": "User"
  },
  {
    "type": "put",
    "url": "http://54.169.225.125:3000/api/user/:username",
    "title": "modify user's info",
    "name": "modify_user_s_info",
    "permission": [
      {
        "name": "private"
      }
    ],
    "group": "User",
    "description": "<p>modify user's infomation!!!</p>",
    "examples": [
      {
        "title": "Example",
        "content": "http://54.169.225.125:3000/api/meodorewan",
        "type": "json"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "age",
            "description": "<p>new age</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>new name</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>successful</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "app/controller/apiController.js",
    "groupTitle": "User"
  }
] });
