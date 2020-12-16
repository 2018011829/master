/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : tiantian

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2020-12-16 14:35:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `head_picture` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'lg', 'lg0816', null);

-- ----------------------------
-- Table structure for `books`
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `introduce` varchar(700) NOT NULL,
  `type` varchar(20) NOT NULL,
  `img` varchar(50) NOT NULL,
  `content` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `grades` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('1', '格林童话', '《格林童话》是由德国语言学家雅可布·格林和威廉·格林兄弟收集、整理、加工完成的德国民间文学。《格林童话》里面约有200多个故事，大部分源自民间的口头传说，其中的《灰姑娘》、《白雪公主》、《小红帽》、《青蛙王子》等童话故事较为闻名。它是世界童话的经典之作，自问世以来，在世界各地影响十分广泛。格林兄弟以其丰富的想象、优美的语言给孩子们讲述了一个个神奇而又浪漫的童话故事。', '童话', 'gelintonghua.png', '格林童话.txt', '雅可布·格林和威廉·格林兄弟', 'big');
INSERT INTO `books` VALUES ('2', '安徒生童话', '《安徒生童话》是丹麦作家安徒生创作的童话集，共由166篇故事组成。该bai作爱憎分明，热情歌颂劳动人民、赞美他们的善良和纯洁的优秀品德；无情地揭露和批判王公贵族们的愚蠢、无能、贪婪和残暴。其中较为闻名的故事有：《小人鱼》、《丑小鸭》、《卖火柴的小女孩》、《拇指姑娘》等。《安徒生童话》已经被译为150多种语言出版发行。他的童话故事还激发了大量电影、芭蕾舞剧、舞台剧以及电影动画的制作。', '童话', 'antushengtonghua.png', '安徒生童话集.txt', '安徒生', 'small');
INSERT INTO `books` VALUES ('3', '意大利童话', '意大利是文艺复兴的发源地，也是欧洲童话的故乡，童话在意大利的产生远比其他欧洲国家要早得多。也许同意大利民族热烈奔放的性格有关，意大利童话中对于真理、正义、善良、慷慨、真诚、勤劳、勇敢等优秀品质的拥护和赞颂更加热情，同时对于虚伪、邪恶、凶残、贪婪、狡猾、懒惰、怯懦等丑陋品质的憎恶和谴责也更加严厉。', '童话', 'yidalitonghua.jpg', '意大利童话.txt', '伊塔洛·卡尔维诺', 'big');
INSERT INTO `books` VALUES ('4', '十二生肖的故事', '中国传统文化中有很多独特、有趣的内容，十二生肖文化就是其中之一。子鼠、丑牛、寅虎、卯兔、辰龙、巳蛇、午马、未羊、申猴、酉鸡、戌狗、亥猪，十二种动物与十二地支相配，形成有意思的组合，而在这种组合背后还有着丰富的内涵和深厚的文化积淀。《十二生肖的故事》通过故事的形式，讲述十二生肖背后的文化内涵，使学生了解生肖不同的象征意义，体悟其中蕴含的智慧、勤劳、质朴、亲情等中华民族传统美德，体验中华祖先文化遗泽的博大。', '童话', 'shiershengxiaodegushi.jpg', '十二生肖的故事.txt', '成坊，姚勤 ', 'small');
INSERT INTO `books` VALUES ('5', '洋葱头历险记', '该作讲述主人公洋葱头，是一个有主张、有正义感的男孩。洋葱头的正义感和智慧可以在各个方面表现出来。老洋葱不小心踩了柠檬王一脚而被关进了监狱。洋葱头来探监时老洋葱告诉他，关押在这个监狱里的都是可怜的人，他们都没有犯罪，可是做坏事的那些人却被养在皇宫里。洋葱头决定救出监狱里善良的人们，却不幸也被投入黑牢。在鼹鼠太太、小樱桃和小草莓的帮助下，洋葱头和朋友们最后终于逃出了监狱，然后又和村民们一起占领了城堡，最后柠檬兵们也纷纷放下武器投降了。大家团结起来，一起推翻了柠檬王的统治，获得了自由。', '童话', 'yangcongtoulixianji.jpg', '洋葱头历险记.txt', '贾尼·罗大里', 'small');
INSERT INTO `books` VALUES ('6', '阿拉伯童话', '《阿拉伯童话》是《世界儿童文学名著插图本》丛书系列之一，由人民文学出版社出版，译著者是王瑞琴。阿拉伯民间童话语言浅显直白，但想象离奇夸张，情节特别富于传奇色彩。阿拉伯民间童话的风格迥异，它们通常语言浅显直白，但想象离奇夸张，情节特别富于传奇色彩，同时充满东方式的哲理和隐喻。', '童话', 'alabotonghua.jpg', '阿拉伯童话.txt', '加瑞，译著:王瑞琴', 'big');
INSERT INTO `books` VALUES ('7', '俄罗斯童话', '《俄罗斯童话》精选自俄罗斯民间文学家阿法纳西耶夫精心收集编撰的俄罗斯童话集，书中收录了《青蛙公主》《严塞老人》《渔夫和金鱼的故事》《伊万王子、火鸟和大灰狼的故事》《青铜国、白银国和黄金国》《漂亮的瓦希丽莎》《白野鸭》《魔戒指》等肤灸人口的名篇。', '童话', 'eluositonghua.jpg', '俄罗斯童话.txt', '阿法纳西耶夫亚历山大·尼古拉耶维奇·阿法纳西耶夫，译者:沈志宏', 'small');
INSERT INTO `books` VALUES ('8', '英国童话', '本书收集了87篇童话，除了各种童话版本中收集的英国童话名篇，大部分均是尚未翻译出版的新童话；有的是故事情节引人入胜的散文体，有的是朗朗上口的诗歌和歌谣体，是本书的一大特色。书中内容惩恶扬善，强调理性、韧性、耐性和机会，和欧洲大陆其他各国的童话形成鲜明差别，成为英国童话的特色，自然也是本书在世界各国常版常销的原因。', '童话', 'yingguotonghua.jpg', '英国童话.txt', '约瑟夫·雅各布斯', 'big');
INSERT INTO `books` VALUES ('9', '王尔德童话', '王尔德是19世纪英国最伟大的艺术家之一，以其剧作、诗歌、童话和小说名世。在风流才子那颓废唯美、狷狂放浪的表面姿态下，是一颗纯美纯善，永难泯灭的童心。而这可贵童心一经与卓绝才智结合，便诞生了《王尔德童话》。它不仅为作者奠定了文学声名的基石，更成为世界文学宝库中的传世佳作。其语言纯正优美堪称典范，其意境高洁悠远益人心智，值得向每一个童稚未凿的孩子、每一位葆有赤子之心的成人郑重推荐。本书收录了王尔德《快乐王子》等全部九篇童话。', '童话', 'wangerdetonghua.jpg', '王尔德童话.txt', '奥斯卡·王尔德', 'big');
INSERT INTO `books` VALUES ('10', '小熊维尼', '英国儿童文学家米恩为自己儿子所写的这个系列故事，取材自儿童身边的布偶和以小熊维尼为主的周围各种动物，他们彼此间的友情、爱、勇气，以及充满乐趣的生活，而且含有丰富的符合老庄思想的人生哲理，不仅小孩子喜欢，成年人、老年人也是不可多得好书哦。', '童话', 'xiaoxiongweini.jpg', '小熊维尼.TXT', '斯蒂文·J·安德森', 'small');
INSERT INTO `books` VALUES ('11', '郭楚海童话', '故事屋收集了郭楚海先生的童话类：《小飞行员》《长尾、短尾和黑妮》《当世界上没有动物以后》《豆芽失踪记》《虎皮儿》 《虎王画像》《灰耳朵的故事》《冒牌米老鼠》《小白鼠过生日》《小力克奇遇记》《隐身药》《奇怪的耳机》《音乐家的故事》《龙的故事》《西克和橡皮狗》《吹牛》《睡觉》《吹牛大王旅行记》《西克的故事》《活书包》《积木城堡》《金鱼的故事》《杜杜先生的喷嚏》《灰灰和白白》《兔子狼》《大黑、小黑和细尾巴》等好看的郭楚海童话', '童话', 'guochuhaitonghua.jpg', '郭楚海童话.txt', '郭楚海', 'big');
INSERT INTO `books` VALUES ('12', '大提琴手高修', '《大提琴手高修》并非一个简单的童话故事，而是一篇充满宗教意味的寓言。高修从一个在乐闭中表现最差的乐手，一跃成为被人称颂的好乐手，这除了自身的刻苦努力之外，也是他在小动物们的帮助下，找到症结所在，从而快速提升琴技的结果。提高自身能力，是获得自信的首要条件。', '童话', 'datiqinshougaoxiu.png', '大提琴手高修.txt', '[日本]宫泽贤治', 'small');
INSERT INTO `books` VALUES ('13', '阿甘正传', '阿甘的智商只有两位数，但他天性善良单纯，加上天赋异秉，使他先后成为大学美式足球明星、越战英雄、世界级乒乓球运动员、摔跤选手和商业大亨。阿甘“轰轰烈烈”的传奇一生，其实正是五十年代到七十年代美国历史与社会的缩影，透过阿甘的眼睛，也让我们看到了外在世界的险恶复杂与庸俗市侩，而更觉人性真诚的可贵。', '文学名著', 'aganzhengzhuan.jpg', '阿甘正传.txt', '[美]温斯顿·葛鲁姆', 'small');
INSERT INTO `books` VALUES ('14', '安娜卡列尼娜', '作品讲述了贵族妇女安娜追求爱情幸福，却在卡列宁的虚伪、渥伦斯基的冷漠和自私面前碰得头破血流，最终落得卧轨自杀、陈尸车站的下场。庄园主列文反对土地私有制，抵制资本主义制度，同情贫苦农民，却又无法摆脱贵族习气而陷入无法解脱的矛盾之中。矛盾的时期、矛盾的制度、矛盾的人物、矛盾的心理，使全书在矛盾的漩涡中颠簸。这部小说是新旧交替时期紧张惶恐的俄国社会的写照。', '文学名著', 'annakalienina.jpg', '安娜卡列尼娜.txt', '托尔斯泰', 'big');
INSERT INTO `books` VALUES ('15', '傲慢与偏见', '是英国女小说家简·奥斯汀创作的长篇小说。小说描写了小乡绅班纳特五个待字闺中的千金，主角是二女儿伊丽莎白。她在舞会上认识了达西，但是耳闻他为人傲慢，一直对他心生排斥，经历一番周折，伊丽莎白解除了对达西的偏见，达西也放下傲慢，有情人终成眷属。', '文学名著', 'aomanyupianjian.jpg', '傲慢与偏见.txt', '简·奥斯汀', 'big');
INSERT INTO `books` VALUES ('16', '巴黎圣母院', '《巴黎圣母院》是法国文学家维克多·雨果创作的长篇小说，《巴黎圣母院》以离奇和对比手法写了一个发生在15世纪法国的故事：巴黎圣母院副主教克罗德道貌岸然、蛇蝎心肠，先爱后恨，迫害吉ト赛女郎埃斯梅拉达。面目丑陋、心地善良的敲钟人卡西莫多为救女郎舍身。小说揭露了宗教的虚伪，宣告禁欲主义的破产，歌颂了下层劳动人民的善良、友爱、舍己为人，反映了雨果的人道主义思想。', '文学名著', 'balishengmuyuan.jpg', '巴黎圣母院.txt', '雨果', 'big');
INSERT INTO `books` VALUES ('17', '窗边的小豆豆', '《窗边的小豆豆》是日本作家、主持人黑柳彻子创作的儿童文学作品，这本书讲述了作者上小学时的一段真实的故事：小豆豆（作者）因淘气被原学校退学后，来到巴学园。在小林校长的爱护和引导下，一般人眼里“怪怪”的小豆豆逐渐变成了一个大家都能接受的孩子。巴学园里亲切、随和的教学方式使这里的孩子们度过了人生最美好的时光。', '文学名著', 'chuangbiandexiaodoudou.jpg', '窗边的小豆豆.txt', '黑柳彻子', 'small');
INSERT INTO `books` VALUES ('18', '动物庄园', '《动物庄园》是英国作家乔治·奥威尔创作的中篇小说，该作讲述农场的一群动物成功地进行了一场“革命”，将压榨他们的人类东家赶出农场，建立起一个平等的动物社会。然而，动物领袖，那些聪明的猪们最终却篡夺了革命的果实，成为比人类东家更加独裁和极权的统治者。', '文学名著', 'dongwuzhuangyuan.jpg', '动物庄园.txt', '乔治·奥威尔', 'small');
INSERT INTO `books` VALUES ('19', '风又三郎', '传说每年立春后的第二百零十天左右，也就是九月一日前后，一个名叫风又三郎的风之精灵会降临人间。九月一日那天，学校里真的来了一个名叫又三郎的转校生，这个长着一头诡异红发的男孩，哪怕是开口一笑，也会山风四起，孩子们怀疑他就是风又三郎。后来有一天，他们真的看见他身披玻璃斗篷，脚穿玻璃鞋，一纵身飞上了天空……', '童话', 'fengyousanlang.png', '风又三郎.txt', '[日本]宫泽贤治', 'big');
INSERT INTO `books` VALUES ('20', '古斯柯布多力传记', '在伊哈特卜北部某座大森林中，古斯柯布多力（小栗旬 配音）和父亲（林隆三 配音）、母亲（草刈民代 配音）以及妹妹妮莉（忽那汐里 配音）过着幸福宁静的生活。某一年，严寒席卷森林，饥饿让此地的生灵们倍受煎熬。布多力的父母相继消失在森林深处，只剩下他和年幼的妹妹相依为命...', '童话', 'gusikebuduolizhuan.png', '古斯柯布多力传记.txt', '[日本]宫泽贤治', 'small');
INSERT INTO `books` VALUES ('21', '银河铁道之夜', '长篇童话《银河铁道之夜》是宫泽贤治的代表作，亦是一部广受喜爱的永恒杰作。它凝聚了作者思想、信仰、教养的全部精髓，被视为宫泽贤治作品的集大成者。作品借焦班尼和柯贝内拉之口，探讨了什么是死亡，什么是真正的幸福这一深重的话题。虽为童话，却是随着阅历和年龄的增长，才能真正领悟其真谛的、贯穿读者一生的重量级文学作品。', '童话', 'yinhetiedaozhiye.png', '银河铁道之夜.txt', '[日本]宫泽贤治', 'big');
INSERT INTO `books` VALUES ('22', '白客', '这个故事发生在一个再婚的家庭里。孔若君的父母在他高考之前离婚了。这个打击对孔若君来说实在太大了，尽管被认为是心理素质一惯很好，在高考中还是名落孙山。继父带来一个女儿，殷静，长得如花似玉，可就是对孔若君从不正眼相看，一副居高临下的公主架子，编偏考上了电影学院......', '悬疑推理', 'baike.png', '白客.txt', '郑渊洁', 'big');
INSERT INTO `books` VALUES ('23', '金拇指', '《金拇指》是郑渊洁2001年创作的19万字长篇小说。女一号欧阳宁秀是下岗工人，家境贫寒。一次同学聚会改变了她的命运。偶然的发现使她得知自己的某个大拇指是能洞悉股市未来行情的金拇指。金拇指给她的家庭、友谊、生命制造的悲欢离合可谓登峰造极。', '悬疑推理', 'jinmuzhi.png', '金拇指.txt', '郑渊洁', 'big');
INSERT INTO `books` VALUES ('24', '麦田里的守望者', '该书的主人公霍尔顿是个中学生，出生于富裕的中产阶级家庭。他虽只有16岁，但比常人高一头，整日穿着风衣，戴着猎帽，游游荡荡，不愿读书。他对学校里的一切——老师、同学、功课、球赛......', '文学名著', 'maitianlideshouwangzhe.png', '麦田里的守望者.txt', '【美】杰罗姆·大卫·塞林格', 'small');
INSERT INTO `books` VALUES ('25', '追风筝的人', '小说以第一人称的角度讲述了阿米尔的故事。阿米尔生于1963年喀布尔的一个富人社区中的一个富裕家庭。其父亲是一个正直的普什图人，一名法官的儿子和成功的地毯商人。阿米尔家的仆人阿里的儿子哈桑则是哈扎拉人......', '文学名著', 'zhuifengzhengderen.png', '追风筝的人.txt', '【美】卡勒德·胡赛尼', 'small');
INSERT INTO `books` VALUES ('26', '水浒传', '北宋末年，宋徽宗在位时期，\r\n央视版《水浒传》人物\r\n央视版《水浒传》人物(35张)\r\n天下瘟疫流行，官府无道，官逼民反。在梁山泊聚集起来自江湖上的许多英雄好汉，打州劫府、济困扶贫、严重动摇了北宋朝廷的统治，但宋江对朝廷的投降导致了梁山农民起义最终走向失败的悲惨结局......', '文学名著', 'shuihuzhuan.png', '水浒传.txt', '施耐庵', 'big');
INSERT INTO `books` VALUES ('27', '战争与和平', '该作以1812年的卫国战争为中心，反映从1805到1820年间的重大历史事件。以鲍尔康斯、别祖霍夫、罗斯托夫和库拉金四大贵族的经历为主线，在战争与和平的交替描写中把众多的事件和人物串联起来......', '文学名著', 'zhanzhengyuheping.png', '战争与和平.txt', '[俄]列夫·尼古拉耶维奇·托尔斯泰', 'big');
INSERT INTO `books` VALUES ('28', '罪与罚', '小说描写穷大学生拉斯柯尔尼科夫受无政府主义思想毒害，认为自己可以为所欲为。为生计所迫，他杀死放高利贷的老太婆阿廖娜和她的无辜妹妹丽扎韦塔，制造了一起震惊全俄的凶杀案。经历了一场内心痛苦的忏悔后，他最终......', '文学名著', 'zuiyufa.png', '罪与罚.txt', '[俄]陀思妥耶夫斯基', 'big');
INSERT INTO `books` VALUES ('29', '童年', '该作讲述了阿廖沙（高尔基的乳名）三岁到十岁这一时期的童年生活，生动地再现了19世纪七八十年代沙俄下层人民的生活状况，写出了高尔基对苦难的认识，对社会人生的独特见解，字里行间涌动着一股生生不息的热望与坚强......', '文学名著', 'tongnian.png', '童年.txt', '[苏联]马克西姆·高尔基', 'small');

-- ----------------------------
-- Table structure for `bookshelf`
-- ----------------------------
DROP TABLE IF EXISTS `bookshelf`;
CREATE TABLE `bookshelf` (
  `phone_num` varchar(11) NOT NULL,
  `child_name` varchar(20) NOT NULL,
  `book_name` varchar(50) NOT NULL,
  PRIMARY KEY (`phone_num`,`child_name`,`book_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookshelf
-- ----------------------------
INSERT INTO `bookshelf` VALUES ('18730094415', '小明', '安娜卡列尼娜');
INSERT INTO `bookshelf` VALUES ('18831158249', '小明', '白客');
INSERT INTO `bookshelf` VALUES ('19831127142', '小明', '安娜卡列尼娜');
INSERT INTO `bookshelf` VALUES ('19831127142', '小明', '窗边的小豆豆');

-- ----------------------------
-- Table structure for `books_contents`
-- ----------------------------
DROP TABLE IF EXISTS `books_contents`;
CREATE TABLE `books_contents` (
  `id` double NOT NULL,
  `article_name` varchar(50) NOT NULL,
  `content_name` varchar(50) NOT NULL,
  `start` int(11) NOT NULL,
  PRIMARY KEY (`id`,`article_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of books_contents
-- ----------------------------
INSERT INTO `books_contents` VALUES ('1', '安娜卡列尼娜.txt', '第一部分  第一章', '2');
INSERT INTO `books_contents` VALUES ('2', '安娜卡列尼娜.txt', '第一部分  第二章', '49');
INSERT INTO `books_contents` VALUES ('3', '安娜卡列尼娜.txt', '第一部分  第三章', '130');
INSERT INTO `books_contents` VALUES ('4', '安娜卡列尼娜.txt', '第一部分  第四章', '211');
INSERT INTO `books_contents` VALUES ('5', '安娜卡列尼娜.txt', '第一部分  第五章', '314');
INSERT INTO `books_contents` VALUES ('6', '安娜卡列尼娜.txt', '第一部分  第六章', '489');
INSERT INTO `books_contents` VALUES ('7', '安娜卡列尼娜.txt', '第一部分  第七章', '518');
INSERT INTO `books_contents` VALUES ('8', '安娜卡列尼娜.txt', '第一部分  第八章', '559');
INSERT INTO `books_contents` VALUES ('9', '安娜卡列尼娜.txt', '第一部分  第九章', '636');
INSERT INTO `books_contents` VALUES ('10', '安娜卡列尼娜.txt', '第一部分  第十章', '777');
INSERT INTO `books_contents` VALUES ('11', '安娜卡列尼娜.txt', '第一部分  第十一章', '972');
INSERT INTO `books_contents` VALUES ('12', '安娜卡列尼娜.txt', '第一部分  第十二章', '1079');
INSERT INTO `books_contents` VALUES ('13', '安娜卡列尼娜.txt', '第一部分  第十三章', '1128');
INSERT INTO `books_contents` VALUES ('14', '安娜卡列尼娜.txt', '第一部分  第十四章', '1177');
INSERT INTO `books_contents` VALUES ('15', '安娜卡列尼娜.txt', '第一部分  第十五章', '1344');
INSERT INTO `books_contents` VALUES ('16', '安娜卡列尼娜.txt', '第一部分  第十六章', '1391');
INSERT INTO `books_contents` VALUES ('17', '安娜卡列尼娜.txt', '第一部分  第十七章', '1422');
INSERT INTO `books_contents` VALUES ('18', '安娜卡列尼娜.txt', '第一部分  第十八章', '1513');
INSERT INTO `books_contents` VALUES ('19', '安娜卡列尼娜.txt', '第一部分  第十九章', '1680');
INSERT INTO `books_contents` VALUES ('20', '安娜卡列尼娜.txt', '第一部分  第二十章', '1805');
INSERT INTO `books_contents` VALUES ('21', '安娜卡列尼娜.txt', '第一部分  第二十一章', '1894');
INSERT INTO `books_contents` VALUES ('22', '安娜卡列尼娜.txt', '第一部分  第二十二章', '1955');
INSERT INTO `books_contents` VALUES ('23', '安娜卡列尼娜.txt', '第一部分  第二十三章', '2022');
INSERT INTO `books_contents` VALUES ('24', '安娜卡列尼娜.txt', '第一部分  第二十四章', '2093');
INSERT INTO `books_contents` VALUES ('25', '安娜卡列尼娜.txt', '第一部分  第二十五章', '2169');
INSERT INTO `books_contents` VALUES ('26', '安娜卡列尼娜.txt', '第一部分  第二十六章', '2310');
INSERT INTO `books_contents` VALUES ('27', '安娜卡列尼娜.txt', '第一部分  第二十七章', '2344');
INSERT INTO `books_contents` VALUES ('28', '安娜卡列尼娜.txt', '第一部分  第二十八章', '2375');
INSERT INTO `books_contents` VALUES ('29', '安娜卡列尼娜.txt', '第一部分  第二十九章', '2459');
INSERT INTO `books_contents` VALUES ('30', '安娜卡列尼娜.txt', '第一部分  第三十章', '2473');
INSERT INTO `books_contents` VALUES ('31', '安娜卡列尼娜.txt', '第一部分  第三十一章', '2505');
INSERT INTO `books_contents` VALUES ('32', '安娜卡列尼娜.txt', '第一部分  第三十二章', '2556');
INSERT INTO `books_contents` VALUES ('33', '安娜卡列尼娜.txt', '第一部分  第三十三章', '2604');
INSERT INTO `books_contents` VALUES ('34', '安娜卡列尼娜.txt', '第一部分  第三十四章', '2664');
INSERT INTO `books_contents` VALUES ('35', '安娜卡列尼娜.txt', '第一部分  第三十五章', '2732');
INSERT INTO `books_contents` VALUES ('36', '安娜卡列尼娜.txt', '第一部分  第三十六章', '2805');
INSERT INTO `books_contents` VALUES ('37', '安娜卡列尼娜.txt', '第一部分  第三十七章', '2885');
INSERT INTO `books_contents` VALUES ('38', '安娜卡列尼娜.txt', '第一部分  第三十八章', '2967');
INSERT INTO `books_contents` VALUES ('39', '安娜卡列尼娜.txt', '第一部分  第三十九章', '3024');
INSERT INTO `books_contents` VALUES ('40', '安娜卡列尼娜.txt', '第一部分  第四十章', '3084');
INSERT INTO `books_contents` VALUES ('41', '安娜卡列尼娜.txt', '第一部分  第四十一章', '3226');
INSERT INTO `books_contents` VALUES ('42', '安娜卡列尼娜.txt', '第一部分  第四十二章', '3393');
INSERT INTO `books_contents` VALUES ('43', '安娜卡列尼娜.txt', '第一部分  第四十三章', '3427');
INSERT INTO `books_contents` VALUES ('44', '安娜卡列尼娜.txt', '第一部分  第四十四章', '3493');
INSERT INTO `books_contents` VALUES ('45', '安娜卡列尼娜.txt', '第一部分  第四十五章', '3501');
INSERT INTO `books_contents` VALUES ('46', '安娜卡列尼娜.txt', '第一部分  第四十六章', '3532');
INSERT INTO `books_contents` VALUES ('47', '安娜卡列尼娜.txt', '第一部分  第四十七章', '3542');
INSERT INTO `books_contents` VALUES ('48', '安娜卡列尼娜.txt', '第一部分  第四十八章', '3698');
INSERT INTO `books_contents` VALUES ('49', '安娜卡列尼娜.txt', '第一部分  第四十九章', '3801');
INSERT INTO `books_contents` VALUES ('50', '安娜卡列尼娜.txt', '第一部分  第五十章', '3869');
INSERT INTO `books_contents` VALUES ('51', '安娜卡列尼娜.txt', '第一部分  第五十一章', '3965');
INSERT INTO `books_contents` VALUES ('52', '安娜卡列尼娜.txt', '第一部分  第五十二章', '4053');
INSERT INTO `books_contents` VALUES ('53', '安娜卡列尼娜.txt', '第一部分  第五十三章', '4074');
INSERT INTO `books_contents` VALUES ('54', '安娜卡列尼娜.txt', '第一部分  第五十四章', '4148');
INSERT INTO `books_contents` VALUES ('55', '安娜卡列尼娜.txt', '第一部分  第五十五章', '4254');
INSERT INTO `books_contents` VALUES ('56', '安娜卡列尼娜.txt', '第一部分  第五十六章', '4335');
INSERT INTO `books_contents` VALUES ('57', '安娜卡列尼娜.txt', '第一部分  第五十七章', '4427');
INSERT INTO `books_contents` VALUES ('58', '安娜卡列尼娜.txt', '第一部分  第五十八章', '4485');
INSERT INTO `books_contents` VALUES ('59', '安娜卡列尼娜.txt', '第一部分  第五十九章', '4575');
INSERT INTO `books_contents` VALUES ('60', '安娜卡列尼娜.txt', '第一部分  第六十章', '4626');
INSERT INTO `books_contents` VALUES ('61', '安娜卡列尼娜.txt', '第一部分  第六十一章', '4666');
INSERT INTO `books_contents` VALUES ('62', '安娜卡列尼娜.txt', '第一部分  第六十二章', '4732');
INSERT INTO `books_contents` VALUES ('63', '安娜卡列尼娜.txt', '第一部分  第六十三章', '4810');
INSERT INTO `books_contents` VALUES ('64', '安娜卡列尼娜.txt', '第一部分  第六十四章', '4913');
INSERT INTO `books_contents` VALUES ('65', '安娜卡列尼娜.txt', '第一部分  第六十五章', '4941');
INSERT INTO `books_contents` VALUES ('66', '安娜卡列尼娜.txt', '第一部分  第六十六章', '5039');
INSERT INTO `books_contents` VALUES ('67', '安娜卡列尼娜.txt', '第一部分  第六十七章', '5160');
INSERT INTO `books_contents` VALUES ('68', '安娜卡列尼娜.txt', '第一部分  第六十八章', '5208');
INSERT INTO `books_contents` VALUES ('69', '安娜卡列尼娜.txt', '第一部分  第六十九章', '5360');
INSERT INTO `books_contents` VALUES ('70', '安娜卡列尼娜.txt', '第一部分  第七十章', '5513');
INSERT INTO `books_contents` VALUES ('71', '安娜卡列尼娜.txt', '第一部分  第七十一章', '5534');
INSERT INTO `books_contents` VALUES ('72', '安娜卡列尼娜.txt', '第一部分  第七十二章', '5576');
INSERT INTO `books_contents` VALUES ('73', '安娜卡列尼娜.txt', '第一部分  第七十三章', '5704');
INSERT INTO `books_contents` VALUES ('74', '安娜卡列尼娜.txt', '第一部分  第七十四章', '5809');
INSERT INTO `books_contents` VALUES ('75', '安娜卡列尼娜.txt', '第一部分  第六章', '5875');
INSERT INTO `books_contents` VALUES ('76', '安娜卡列尼娜.txt', '第一部分  第七十五章', '5967');
INSERT INTO `books_contents` VALUES ('77', '安娜卡列尼娜.txt', '第一部分  第七十六章', '5992');
INSERT INTO `books_contents` VALUES ('78', '安娜卡列尼娜.txt', '第一部分  第七十七章', '6062');
INSERT INTO `books_contents` VALUES ('79', '安娜卡列尼娜.txt', '第一部分  第七十八章', '6122');
INSERT INTO `books_contents` VALUES ('80', '安娜卡列尼娜.txt', '第一部分  第七十九章', '6231');
INSERT INTO `books_contents` VALUES ('81', '安娜卡列尼娜.txt', '第一部分  第八十章', '6267');
INSERT INTO `books_contents` VALUES ('82', '安娜卡列尼娜.txt', '第一部分  第八十一章', '6307');
INSERT INTO `books_contents` VALUES ('83', '安娜卡列尼娜.txt', '第一部分  第八十二章', '6350');
INSERT INTO `books_contents` VALUES ('84', '安娜卡列尼娜.txt', '第一部分  第八十三章', '6378');
INSERT INTO `books_contents` VALUES ('85', '安娜卡列尼娜.txt', '第一部分  第八十四章', '6444');
INSERT INTO `books_contents` VALUES ('86', '安娜卡列尼娜.txt', '第一部分  第八十五章', '6487');
INSERT INTO `books_contents` VALUES ('87', '安娜卡列尼娜.txt', '第一部分  第八十六章', '6567');
INSERT INTO `books_contents` VALUES ('88', '安娜卡列尼娜.txt', '第一部分  第八十七章', '6653');
INSERT INTO `books_contents` VALUES ('89', '安娜卡列尼娜.txt', '第一部分  第八十八章', '6668');
INSERT INTO `books_contents` VALUES ('90', '安娜卡列尼娜.txt', '第一部分  第八十九章', '6692');
INSERT INTO `books_contents` VALUES ('91', '安娜卡列尼娜.txt', '第一部分  第九十章', '6852');
INSERT INTO `books_contents` VALUES ('92', '安娜卡列尼娜.txt', '第一部分  第九十一章', '6943');
INSERT INTO `books_contents` VALUES ('93', '安娜卡列尼娜.txt', '第一部分  第九十二章', '6991');
INSERT INTO `books_contents` VALUES ('94', '安娜卡列尼娜.txt', '第一部分  第九十三章', '7005');
INSERT INTO `books_contents` VALUES ('95', '安娜卡列尼娜.txt', '第一部分  第九十四章', '7058');
INSERT INTO `books_contents` VALUES ('96', '安娜卡列尼娜.txt', '第一部分  第九十五章', '7098');
INSERT INTO `books_contents` VALUES ('97', '安娜卡列尼娜.txt', '第一部分  第九十六章', '7228');
INSERT INTO `books_contents` VALUES ('98', '安娜卡列尼娜.txt', '第一部分  第九十七章', '7303');
INSERT INTO `books_contents` VALUES ('99', '安娜卡列尼娜.txt', '第一部分  第九十八章', '7339');
INSERT INTO `books_contents` VALUES ('100', '安娜卡列尼娜.txt', '第一部分  第九十九章', '7393');
INSERT INTO `books_contents` VALUES ('101', '安娜卡列尼娜.txt', '第二部分  第一章', '7536');
INSERT INTO `books_contents` VALUES ('102', '安娜卡列尼娜.txt', '第二部分  第二章', '7553');
INSERT INTO `books_contents` VALUES ('103', '安娜卡列尼娜.txt', '第二部分  第三章', '7579');
INSERT INTO `books_contents` VALUES ('104', '安娜卡列尼娜.txt', '第二部分  第四章', '7725');
INSERT INTO `books_contents` VALUES ('105', '安娜卡列尼娜.txt', '第二部分  第五章', '7802');
INSERT INTO `books_contents` VALUES ('106', '安娜卡列尼娜.txt', '第二部分  第六章', '7898');
INSERT INTO `books_contents` VALUES ('107', '安娜卡列尼娜.txt', '第二部分  第七章', '7960');
INSERT INTO `books_contents` VALUES ('108', '安娜卡列尼娜.txt', '第二部分  第八章', '8043');
INSERT INTO `books_contents` VALUES ('109', '安娜卡列尼娜.txt', '第二部分  第九章', '8137');
INSERT INTO `books_contents` VALUES ('110', '安娜卡列尼娜.txt', '第二部分  第十章', '8249');
INSERT INTO `books_contents` VALUES ('111', '安娜卡列尼娜.txt', '第二部分  第十一章', '8342');
INSERT INTO `books_contents` VALUES ('112', '安娜卡列尼娜.txt', '第二部分  第十二章', '8380');
INSERT INTO `books_contents` VALUES ('113', '安娜卡列尼娜.txt', '第二部分  第十三章', '8482');
INSERT INTO `books_contents` VALUES ('114', '安娜卡列尼娜.txt', '第二部分  第十四章', '8569');
INSERT INTO `books_contents` VALUES ('115', '安娜卡列尼娜.txt', '第二部分  第十五章', '8643');
INSERT INTO `books_contents` VALUES ('116', '安娜卡列尼娜.txt', '第二部分  第十六章', '8701');
INSERT INTO `books_contents` VALUES ('117', '安娜卡列尼娜.txt', '第二部分  第十七章', '8782');
INSERT INTO `books_contents` VALUES ('118', '安娜卡列尼娜.txt', '第二部分  第十八章', '8908');
INSERT INTO `books_contents` VALUES ('119', '安娜卡列尼娜.txt', '第二部分  第十九章', '8945');
INSERT INTO `books_contents` VALUES ('120', '安娜卡列尼娜.txt', '第二部分  第二十章', '9060');
INSERT INTO `books_contents` VALUES ('121', '安娜卡列尼娜.txt', '第二部分  第二十一章', '9098');
INSERT INTO `books_contents` VALUES ('122', '安娜卡列尼娜.txt', '第二部分  第二十二章', '9186');
INSERT INTO `books_contents` VALUES ('123', '安娜卡列尼娜.txt', '第二部分  第二十三章', '9273');
INSERT INTO `books_contents` VALUES ('124', '安娜卡列尼娜.txt', '第二部分  第一章', '9349');
INSERT INTO `books_contents` VALUES ('125', '安娜卡列尼娜.txt', '第二部分  第二章', '9438');
INSERT INTO `books_contents` VALUES ('126', '安娜卡列尼娜.txt', '第二部分  第三章', '9564');
INSERT INTO `books_contents` VALUES ('127', '安娜卡列尼娜.txt', '第二部分  第四章', '9610');
INSERT INTO `books_contents` VALUES ('128', '安娜卡列尼娜.txt', '第二部分  第五章', '9699');
INSERT INTO `books_contents` VALUES ('129', '安娜卡列尼娜.txt', '第二部分  第六章', '9779');
INSERT INTO `books_contents` VALUES ('130', '安娜卡列尼娜.txt', '第二部分  第七章', '9807');
INSERT INTO `books_contents` VALUES ('131', '安娜卡列尼娜.txt', '第二部分  第八章', '9898');
INSERT INTO `books_contents` VALUES ('132', '安娜卡列尼娜.txt', '第二部分  第九章', '9916');
INSERT INTO `books_contents` VALUES ('133', '安娜卡列尼娜.txt', '第二部分  第十章', '9972');
INSERT INTO `books_contents` VALUES ('134', '安娜卡列尼娜.txt', '第二部分  第十一章', '10003');
INSERT INTO `books_contents` VALUES ('135', '安娜卡列尼娜.txt', '第二部分  第十二章', '10061');
INSERT INTO `books_contents` VALUES ('136', '安娜卡列尼娜.txt', '第二部分  第十三章', '10093');
INSERT INTO `books_contents` VALUES ('137', '安娜卡列尼娜.txt', '第二部分  第十四章', '10120');
INSERT INTO `books_contents` VALUES ('138', '安娜卡列尼娜.txt', '第二部分  第十五章', '10152');
INSERT INTO `books_contents` VALUES ('139', '安娜卡列尼娜.txt', '第二部分  第十六章', '10216');
INSERT INTO `books_contents` VALUES ('140', '安娜卡列尼娜.txt', '第二部分  第十七章', '10285');
INSERT INTO `books_contents` VALUES ('141', '安娜卡列尼娜.txt', '第二部分  第十八章', '10367');
INSERT INTO `books_contents` VALUES ('142', '安娜卡列尼娜.txt', '第二部分  第十九章', '10409');
INSERT INTO `books_contents` VALUES ('143', '安娜卡列尼娜.txt', '第二部分  第二十章', '10450');
INSERT INTO `books_contents` VALUES ('144', '安娜卡列尼娜.txt', '第二部分  第二十一章', '10576');
INSERT INTO `books_contents` VALUES ('145', '安娜卡列尼娜.txt', '第二部分  第二十二章', '10616');
INSERT INTO `books_contents` VALUES ('146', '安娜卡列尼娜.txt', '第二部分  第二十三章', '10677');
INSERT INTO `books_contents` VALUES ('147', '安娜卡列尼娜.txt', '第二部分  第二十四章', '10711');
INSERT INTO `books_contents` VALUES ('148', '安娜卡列尼娜.txt', '第二部分  第二十五章', '10807');
INSERT INTO `books_contents` VALUES ('149', '安娜卡列尼娜.txt', '第二部分  第二十六章', '10864');
INSERT INTO `books_contents` VALUES ('150', '安娜卡列尼娜.txt', '第二部分  第二十七章', '10956');
INSERT INTO `books_contents` VALUES ('151', '安娜卡列尼娜.txt', '第二部分  第二十八章', '11016');
INSERT INTO `books_contents` VALUES ('152', '安娜卡列尼娜.txt', '第二部分  第二十九章', '11065');
INSERT INTO `books_contents` VALUES ('153', '安娜卡列尼娜.txt', '第二部分  第三十章', '11159');
INSERT INTO `books_contents` VALUES ('154', '安娜卡列尼娜.txt', '第二部分  第三十一章', '11221');
INSERT INTO `books_contents` VALUES ('155', '安娜卡列尼娜.txt', '第二部分  第三十二章', '11282');
INSERT INTO `books_contents` VALUES ('156', '安娜卡列尼娜.txt', '第二部分  第三十三章', '11344');
INSERT INTO `books_contents` VALUES ('157', '安娜卡列尼娜.txt', '第三部分  第一章', '11502');
INSERT INTO `books_contents` VALUES ('158', '安娜卡列尼娜.txt', '第三部分  第二章', '11559');
INSERT INTO `books_contents` VALUES ('159', '安娜卡列尼娜.txt', '第三部分  第三章', '11711');
INSERT INTO `books_contents` VALUES ('160', '安娜卡列尼娜.txt', '第三部分  第四章', '11815');
INSERT INTO `books_contents` VALUES ('161', '安娜卡列尼娜.txt', '第三部分  第五章', '11832');
INSERT INTO `books_contents` VALUES ('162', '安娜卡列尼娜.txt', '第三部分  第六章', '11900');
INSERT INTO `books_contents` VALUES ('163', '安娜卡列尼娜.txt', '第三部分  第七章', '12002');
INSERT INTO `books_contents` VALUES ('164', '安娜卡列尼娜.txt', '第三部分  第八章', '12143');
INSERT INTO `books_contents` VALUES ('165', '安娜卡列尼娜.txt', '第三部分  第九章', '12217');
INSERT INTO `books_contents` VALUES ('166', '安娜卡列尼娜.txt', '第三部分  第十章', '12299');
INSERT INTO `books_contents` VALUES ('167', '安娜卡列尼娜.txt', '第三部分  第十一章', '12394');
INSERT INTO `books_contents` VALUES ('168', '安娜卡列尼娜.txt', '第三部分  第十二章', '12546');
INSERT INTO `books_contents` VALUES ('169', '安娜卡列尼娜.txt', '第三部分  第十三章', '12592');
INSERT INTO `books_contents` VALUES ('170', '安娜卡列尼娜.txt', '第三部分  第十四章', '12633');
INSERT INTO `books_contents` VALUES ('171', '安娜卡列尼娜.txt', '第三部分  第十五章', '12735');
INSERT INTO `books_contents` VALUES ('172', '安娜卡列尼娜.txt', '第三部分  第十六章', '12879');
INSERT INTO `books_contents` VALUES ('173', '安娜卡列尼娜.txt', '第三部分  第十七章', '12922');
INSERT INTO `books_contents` VALUES ('174', '安娜卡列尼娜.txt', '第三部分  第十八章', '13010');
INSERT INTO `books_contents` VALUES ('175', '安娜卡列尼娜.txt', '第三部分  第十九章', '13098');
INSERT INTO `books_contents` VALUES ('176', '安娜卡列尼娜.txt', '第三部分  第二十章', '13167');
INSERT INTO `books_contents` VALUES ('177', '安娜卡列尼娜.txt', '第三部分  第二十一章', '13263');
INSERT INTO `books_contents` VALUES ('178', '安娜卡列尼娜.txt', '第三部分  第二十二章', '13343');
INSERT INTO `books_contents` VALUES ('179', '安娜卡列尼娜.txt', '第三部分  第二十三章', '13496');
INSERT INTO `books_contents` VALUES ('180', '安娜卡列尼娜.txt', '第三部分  第二十四章', '13616');
INSERT INTO `books_contents` VALUES ('181', '安娜卡列尼娜.txt', '第三部分  第二十五章', '13700');
INSERT INTO `books_contents` VALUES ('182', '安娜卡列尼娜.txt', '第三部分  第二十六章', '13729');
INSERT INTO `books_contents` VALUES ('183', '安娜卡列尼娜.txt', '第三部分  第二十七章', '13755');
INSERT INTO `books_contents` VALUES ('184', '安娜卡列尼娜.txt', '第三部分  第二十八章', '13789');
INSERT INTO `books_contents` VALUES ('185', '安娜卡列尼娜.txt', '第三部分  第二十九章', '13840');
INSERT INTO `books_contents` VALUES ('186', '安娜卡列尼娜.txt', '第三部分  第三十章', '13932');
INSERT INTO `books_contents` VALUES ('187', '安娜卡列尼娜.txt', '第三部分  第三十一章', '14064');
INSERT INTO `books_contents` VALUES ('188', '安娜卡列尼娜.txt', '第三部分  第三十二章', '14115');
INSERT INTO `books_contents` VALUES ('189', '安娜卡列尼娜.txt', '第四部分  第一章', '14207');
INSERT INTO `books_contents` VALUES ('190', '安娜卡列尼娜.txt', '第四部分  第二章', '14240');
INSERT INTO `books_contents` VALUES ('191', '安娜卡列尼娜.txt', '第四部分  第三章', '14356');
INSERT INTO `books_contents` VALUES ('192', '安娜卡列尼娜.txt', '第四部分  第四章', '14419');
INSERT INTO `books_contents` VALUES ('193', '安娜卡列尼娜.txt', '第四部分  第五章', '14507');
INSERT INTO `books_contents` VALUES ('194', '安娜卡列尼娜.txt', '第四部分  第六章', '14550');
INSERT INTO `books_contents` VALUES ('195', '安娜卡列尼娜.txt', '第四部分  第七章', '14608');
INSERT INTO `books_contents` VALUES ('196', '安娜卡列尼娜.txt', '第四部分  第八章', '14685');
INSERT INTO `books_contents` VALUES ('197', '安娜卡列尼娜.txt', '第四部分  第九章', '14761');
INSERT INTO `books_contents` VALUES ('198', '安娜卡列尼娜.txt', '第四部分  第十章', '14802');
INSERT INTO `books_contents` VALUES ('199', '安娜卡列尼娜.txt', '第四部分  第十一章', '14926');
INSERT INTO `books_contents` VALUES ('200', '安娜卡列尼娜.txt', '第四部分  第十二章', '14981');
INSERT INTO `books_contents` VALUES ('201', '安娜卡列尼娜.txt', '第四部分  第十三章', '15031');
INSERT INTO `books_contents` VALUES ('202', '安娜卡列尼娜.txt', '第四部分  第十四章', '15094');
INSERT INTO `books_contents` VALUES ('203', '安娜卡列尼娜.txt', '第四部分  第十五章', '15180');
INSERT INTO `books_contents` VALUES ('204', '安娜卡列尼娜.txt', '第四部分  第十六章', '15219');
INSERT INTO `books_contents` VALUES ('205', '安娜卡列尼娜.txt', '第四部分  第十七章', '15275');
INSERT INTO `books_contents` VALUES ('206', '安娜卡列尼娜.txt', '第四部分  第十八章', '15344');
INSERT INTO `books_contents` VALUES ('207', '安娜卡列尼娜.txt', '第四部分  第十九章', '15414');
INSERT INTO `books_contents` VALUES ('208', '安娜卡列尼娜.txt', '第四部分  第二十章', '15479');
INSERT INTO `books_contents` VALUES ('209', '安娜卡列尼娜.txt', '第四部分  第二十一章', '15561');
INSERT INTO `books_contents` VALUES ('210', '安娜卡列尼娜.txt', '第四部分  第二十二章', '15695');
INSERT INTO `books_contents` VALUES ('211', '安娜卡列尼娜.txt', '第四部分  第二十三章', '15740');
INSERT INTO `books_contents` VALUES ('212', '安娜卡列尼娜.txt', '第四部分  第二十四章', '15786');
INSERT INTO `books_contents` VALUES ('213', '安娜卡列尼娜.txt', '第四部分  第二十五章', '15902');
INSERT INTO `books_contents` VALUES ('214', '安娜卡列尼娜.txt', '第四部分  第二十六章', '16052');
INSERT INTO `books_contents` VALUES ('215', '安娜卡列尼娜.txt', '第四部分  第二十七章', '16110');
INSERT INTO `books_contents` VALUES ('216', '安娜卡列尼娜.txt', '第四部分  第二十八章', '16177');
INSERT INTO `books_contents` VALUES ('217', '安娜卡列尼娜.txt', '第四部分  第二十九章', '16259');
INSERT INTO `books_contents` VALUES ('218', '安娜卡列尼娜.txt', '第四部分  第三十章', '16297');
INSERT INTO `books_contents` VALUES ('219', '安娜卡列尼娜.txt', '第四部分  第三十一章', '16317');
INSERT INTO `books_contents` VALUES ('220', '安娜卡列尼娜.txt', '第五部分  第一章', '16369');
INSERT INTO `books_contents` VALUES ('221', '安娜卡列尼娜.txt', '第五部分  第二章', '16430');
INSERT INTO `books_contents` VALUES ('222', '安娜卡列尼娜.txt', '第五部分  第三章', '16512');
INSERT INTO `books_contents` VALUES ('223', '安娜卡列尼娜.txt', '第五部分  第四章', '16556');
INSERT INTO `books_contents` VALUES ('224', '安娜卡列尼娜.txt', '第五部分  第五章', '16593');
INSERT INTO `books_contents` VALUES ('225', '安娜卡列尼娜.txt', '第五部分  第六章', '16637');
INSERT INTO `books_contents` VALUES ('226', '安娜卡列尼娜.txt', '第五部分  第七章', '16699');
INSERT INTO `books_contents` VALUES ('227', '安娜卡列尼娜.txt', '第五部分  第八章', '16724');
INSERT INTO `books_contents` VALUES ('228', '安娜卡列尼娜.txt', '第五部分  第九章', '16750');
INSERT INTO `books_contents` VALUES ('229', '安娜卡列尼娜.txt', '第五部分  第十章', '16790');
INSERT INTO `books_contents` VALUES ('230', '安娜卡列尼娜.txt', '第五部分  第十一章', '16823');
INSERT INTO `books_contents` VALUES ('231', '安娜卡列尼娜.txt', '第五部分  第十二章', '16871');
INSERT INTO `books_contents` VALUES ('232', '安娜卡列尼娜.txt', '第五部分  第十三章', '16927');
INSERT INTO `books_contents` VALUES ('233', '安娜卡列尼娜.txt', '第五部分  第十四章', '16970');
INSERT INTO `books_contents` VALUES ('234', '安娜卡列尼娜.txt', '第五部分  第十五章', '17074');
INSERT INTO `books_contents` VALUES ('235', '安娜卡列尼娜.txt', '第五部分  第十六章', '17164');
INSERT INTO `books_contents` VALUES ('236', '安娜卡列尼娜.txt', '第五部分  第十七章', '17229');
INSERT INTO `books_contents` VALUES ('237', '安娜卡列尼娜.txt', '第五部分  第十八章', '17275');
INSERT INTO `books_contents` VALUES ('238', '安娜卡列尼娜.txt', '第五部分  第十九章', '17327');
INSERT INTO `books_contents` VALUES ('1', '阿甘正传.txt', '第一章  妈妈送我上学', '6');
INSERT INTO `books_contents` VALUES ('2', '阿甘正传.txt', '第二章  参加足球明星盛会', '92');
INSERT INTO `books_contents` VALUES ('3', '阿甘正传.txt', '第三章  布莱恩教练来了体育馆', '172');
INSERT INTO `books_contents` VALUES ('4', '阿甘正传.txt', '第四章  布莱恩教练的秘招', '252');
INSERT INTO `books_contents` VALUES ('5', '阿甘正传.txt', '第五章  我的体育成绩', '346');
INSERT INTO `books_contents` VALUES ('6', '阿甘正传.txt', '第六章  战场上的我', '532');
INSERT INTO `books_contents` VALUES ('7', '阿甘正传.txt', '第七章  岘港的医院', '614');
INSERT INTO `books_contents` VALUES ('8', '阿甘正传.txt', '第八章  回美国', '692');
INSERT INTO `books_contents` VALUES ('9', '阿甘正传.txt', '第九章  来到中国', '790');
INSERT INTO `books_contents` VALUES ('10', '阿甘正传.txt', '第十章  寻找珍妮', '870');
INSERT INTO `books_contents` VALUES ('11', '阿甘正传.txt', '第十一章  在哈佛奎肯布希教授课堂上演戏', '970');
INSERT INTO `books_contents` VALUES ('12', '阿甘正传.txt', '第十二章  真正的疯人院', '1118');
INSERT INTO `books_contents` VALUES ('13', '阿甘正传.txt', '第十三章  我的第一印象', '1232');
INSERT INTO `books_contents` VALUES ('14', '阿甘正传.txt', '第十四章  降落的情况', '1324');
INSERT INTO `books_contents` VALUES ('15', '阿甘正传.txt', '第十五章  种棉花', '1482');
INSERT INTO `books_contents` VALUES ('16', '阿甘正传.txt', '第十六章  大山姆的全族人', '1606');
INSERT INTO `books_contents` VALUES ('17', '阿甘正传.txt', '第十七章  我的暖气栅', '1780');
INSERT INTO `books_contents` VALUES ('18', '阿甘正传.txt', '第十八章  我毕生最快乐的重逢', '1926');
INSERT INTO `books_contents` VALUES ('19', '阿甘正传.txt', '第十九章  蒙夕的比赛预定结果', '2072');
INSERT INTO `books_contents` VALUES ('20', '阿甘正传.txt', '第二十章  麦克把丹和我叫到他的办公室', '2200');
INSERT INTO `books_contents` VALUES ('21', '阿甘正传.txt', '第廿一章  我成了一个可怜的混球', '2404');
INSERT INTO `books_contents` VALUES ('22', '阿甘正传.txt', '第廿二章  棋赛', '2542');
INSERT INTO `books_contents` VALUES ('23', '阿甘正传.txt', '第廿三章  我又进了牢房', '2714');
INSERT INTO `books_contents` VALUES ('24', '阿甘正传.txt', '第廿四章  我重返家乡了', '2844');
INSERT INTO `books_contents` VALUES ('25', '阿甘正传.txt', '第廿五章  六月的一个非常舒爽的日子', '2966');
INSERT INTO `books_contents` VALUES ('26', '阿甘正传.txt', '第廿六章  沙凡纳车站', '3140');
INSERT INTO `books_contents` VALUES ('1', '阿拉伯童话.txt', '章节：1獴的故事', '25');
INSERT INTO `books_contents` VALUES ('2', '阿拉伯童话.txt', '章节：2老鼠和黄鼠狼的故事', '70');
INSERT INTO `books_contents` VALUES ('3', '阿拉伯童话.txt', '章节：3穷人的幸福', '112');
INSERT INTO `books_contents` VALUES ('4', '阿拉伯童话.txt', '章节：4狐狸如何欺骗狮子', '147');
INSERT INTO `books_contents` VALUES ('5', '阿拉伯童话.txt', '章节：5松鸡和乌龟的故事', '186');
INSERT INTO `books_contents` VALUES ('6', '阿拉伯童话.txt', '章节：6阿里失窃案', '255');
INSERT INTO `books_contents` VALUES ('7', '阿拉伯童话.txt', '章节：7箭猪和斑鸠的故事', '357');
INSERT INTO `books_contents` VALUES ('8', '阿拉伯童话.txt', '章节：8渔人与海龟的故事', '442');
INSERT INTO `books_contents` VALUES ('9', '阿拉伯童话.txt', '章节：9背信弃义的狼', '544');
INSERT INTO `books_contents` VALUES ('10', '阿拉伯童话.txt', '章节：10穷汉的木碗', '666');
INSERT INTO `books_contents` VALUES ('11', '阿拉伯童话.txt', '章节：11魔鬼的井', '787');
INSERT INTO `books_contents` VALUES ('12', '阿拉伯童话.txt', '章节：12第九尊雕像', '928');
INSERT INTO `books_contents` VALUES ('13', '阿拉伯童话.txt', '章节：13阿里父子沉浮记', '1064');
INSERT INTO `books_contents` VALUES ('14', '阿拉伯童话.txt', '章节：14忘恩负义的阿卜杜拉', '1264');
INSERT INTO `books_contents` VALUES ('15', '阿拉伯童话.txt', '章节：15女人和她的五个追求者的故事', '1482');
INSERT INTO `books_contents` VALUES ('16', '阿拉伯童话.txt', '章节：16真正的朋友', '1711');
INSERT INTO `books_contents` VALUES ('17', '阿拉伯童话.txt', '章节：17陆上阿卜杜拉和海中阿卜杜拉', '1924');
INSERT INTO `books_contents` VALUES ('18', '阿拉伯童话.txt', '章节：18鸟兽和木匠的故事', '2195');
INSERT INTO `books_contents` VALUES ('19', '阿拉伯童话.txt', '章节：19猴王子', '2468');
INSERT INTO `books_contents` VALUES ('20', '阿拉伯童话.txt', '章节：20裁缝和儿子', '2799');
INSERT INTO `books_contents` VALUES ('21', '阿拉伯童话.txt', '章节：21非洲魔法师', '2830');
INSERT INTO `books_contents` VALUES ('22', '阿拉伯童话.txt', '章节：22神奇的灯', '2990');
INSERT INTO `books_contents` VALUES ('23', '阿拉伯童话.txt', '章节：23穆哈默德的奇遇', '3121');
INSERT INTO `books_contents` VALUES ('24', '阿拉伯童话.txt', '章节：24阿里巴巴和四十大盗', '3490');
INSERT INTO `books_contents` VALUES ('25', '阿拉伯童话.txt', '章节：25阿布努瓦斯的故事', '3827');
INSERT INTO `books_contents` VALUES ('26', '阿拉伯童话.txt', '章节：26苏里曼大帝的指环', '4279');
INSERT INTO `books_contents` VALUES ('27', '阿拉伯童话.txt', '章节：27渔夫的故事', '4757');
INSERT INTO `books_contents` VALUES ('28', '阿拉伯童话.txt', '章节：28森露蒂和大法官', '5256');
INSERT INTO `books_contents` VALUES ('29', '阿拉伯童话.txt', '章节：29铜城的传说', '5859');
INSERT INTO `books_contents` VALUES ('30', '阿拉伯童话.txt', '章节：30辛伯达航海记', '6453');
INSERT INTO `books_contents` VALUES ('1', '安徒生童话集.txt', '第一章 打火匣', '1');
INSERT INTO `books_contents` VALUES ('2', '安徒生童话集.txt', '第二章 皇帝的新装', '109');
INSERT INTO `books_contents` VALUES ('3', '安徒生童话集.txt', '第三章 飞箱', '193');
INSERT INTO `books_contents` VALUES ('4', '安徒生童话集.txt', '第四章 丑小鸭', '303');
INSERT INTO `books_contents` VALUES ('5', '安徒生童话集.txt', '第五章 没有画的画册', '453');
INSERT INTO `books_contents` VALUES ('6', '安徒生童话集.txt', '第六章 跳高者', '887');
INSERT INTO `books_contents` VALUES ('7', '安徒生童话集.txt', '第七章 红鞋', '931');
INSERT INTO `books_contents` VALUES ('8', '安徒生童话集.txt', '第八章 衬衫领子', '1029');
INSERT INTO `books_contents` VALUES ('9', '安徒生童话集.txt', '第九章 一个豆荚里的五粒豆', '1093');
INSERT INTO `books_contents` VALUES ('10', '安徒生童话集.txt', '第十章 一个贵族和他的女儿们', '1163');
INSERT INTO `books_contents` VALUES ('11', '安徒生童话集.txt', '第十一章 守塔人奥列', '1291');
INSERT INTO `books_contents` VALUES ('12', '安徒生童话集.txt', '第十二章 蝴蝶', '1357');
INSERT INTO `books_contents` VALUES ('13', '安徒生童话集.txt', '第十三章 贝脱、比脱和比尔', '1415');
INSERT INTO `books_contents` VALUES ('14', '安徒生童话集.txt', '第十四章 烂布片', '1469');
INSERT INTO `books_contents` VALUES ('15', '安徒生童话集.txt', '第十五章 织补针', '1497');
INSERT INTO `books_contents` VALUES ('16', '安徒生童话集.txt', '第十六章 拇指姑娘', '1575');
INSERT INTO `books_contents` VALUES ('17', '安徒生童话集.txt', '第十七章 跳蚤和教授', '1715');
INSERT INTO `books_contents` VALUES ('18', '安徒生童话集.txt', '第十八章 区别', '1799');
INSERT INTO `books_contents` VALUES ('19', '安徒生童话集.txt', '第十九章 一本不说话的书', '1855');
INSERT INTO `books_contents` VALUES ('20', '安徒生童话集.txt', '第二十章 夏日痴', '1881');
INSERT INTO `books_contents` VALUES ('21', '安徒生童话集.txt', '第二十一章 笔和墨水壶', '1941');
INSERT INTO `books_contents` VALUES ('22', '安徒生童话集.txt', '第二十二章 风车', '1977');
INSERT INTO `books_contents` VALUES ('23', '安徒生童话集.txt', '第二十三章 瓦尔都窗前的一瞥', '2005');
INSERT INTO `books_contents` VALUES ('24', '安徒生童话集.txt', '第二十四章 甲虫', '2029');
INSERT INTO `books_contents` VALUES ('25', '安徒生童话集.txt', '第二十五章 幸福的家庭', '2201');
INSERT INTO `books_contents` VALUES ('26', '安徒生童话集.txt', '第二十六章 最后的一天', '2257');
INSERT INTO `books_contents` VALUES ('27', '安徒生童话集.txt', '第二十七章 完全是真的', '2337');
INSERT INTO `books_contents` VALUES ('28', '安徒生童话集.txt', '第二十八章 蓟的遭遇', '2381');
INSERT INTO `books_contents` VALUES ('29', '安徒生童话集.txt', '第二十九章 新世纪的女神', '2467');
INSERT INTO `books_contents` VALUES ('30', '安徒生童话集.txt', '第三十章 一星期的日子', '2635');
INSERT INTO `books_contents` VALUES ('31', '安徒生童话集.txt', '第三十一章 钱猪', '2675');
INSERT INTO `books_contents` VALUES ('32', '安徒生童话集.txt', '第三十二章 在辽远的海极', '2711');
INSERT INTO `books_contents` VALUES ('33', '安徒生童话集.txt', '第三十三章 荷马墓上的一朵玫瑰', '2733');
INSERT INTO `books_contents` VALUES ('34', '安徒生童话集.txt', '第三十四章 野天鹅', '2759');
INSERT INTO `books_contents` VALUES ('35', '安徒生童话集.txt', '第三十五章 母亲的故事', '2947');
INSERT INTO `books_contents` VALUES ('36', '安徒生童话集.txt', '第三十六章 犹太女子', '3055');
INSERT INTO `books_contents` VALUES ('37', '安徒生童话集.txt', '第三十七章 牙痛姑妈', '3133');
INSERT INTO `books_contents` VALUES ('38', '安徒生童话集.txt', '第三十八章 金黄的宝贝', '3375');
INSERT INTO `books_contents` VALUES ('39', '安徒生童话集.txt', '第三十九章 民歌的鸟儿', '3549');
INSERT INTO `books_contents` VALUES ('40', '安徒生童话集.txt', '第四十章 接骨木树妈妈', '3603');
INSERT INTO `books_contents` VALUES ('41', '安徒生童话集.txt', '第四十一章 沙丘的故事', '3731');
INSERT INTO `books_contents` VALUES ('42', '安徒生童话集.txt', '第四十三章 迁居的日子', '4403');
INSERT INTO `books_contents` VALUES ('43', '安徒生童话集.txt', '第四十四章 鬼火进城了', '4459');
INSERT INTO `books_contents` VALUES ('44', '安徒生童话集.txt', '第四十五章 幸运的套鞋', '4627');
INSERT INTO `books_contents` VALUES ('45', '安徒生童话集.txt', '第四十六章 鹳鸟', '5341');
INSERT INTO `books_contents` VALUES ('46', '安徒生童话集.txt', '第四十七章 枞树', '5459');
INSERT INTO `books_contents` VALUES ('47', '安徒生童话集.txt', '第四十八章 香肠栓熬的汤①', '5629');
INSERT INTO `books_contents` VALUES ('48', '安徒生童话集.txt', '第五十章 亚麻', '5873');
INSERT INTO `books_contents` VALUES ('49', '安徒生童话集.txt', '第五十一章 天上落下来的一片叶子', '5957');
INSERT INTO `books_contents` VALUES ('50', '安徒生童话集.txt', '第五十二章 恶毒的王子', '6017');
INSERT INTO `books_contents` VALUES ('51', '安徒生童话集.txt', '第五十三章 演木偶戏的人', '6047');
INSERT INTO `books_contents` VALUES ('52', '安徒生童话集.txt', '第五十四章 舞吧，舞吧，我的玩偶', '6097');
INSERT INTO `books_contents` VALUES ('53', '安徒生童话集.txt', '第五十五章 安妮·莉斯贝', '6163');
INSERT INTO `books_contents` VALUES ('54', '安徒生童话集.txt', '第五十六章 素琪①', '6311');
INSERT INTO `books_contents` VALUES ('55', '安徒生童话集.txt', '第五十七章 藏着并不等于遗忘', '6513');
INSERT INTO `books_contents` VALUES ('56', '安徒生童话集.txt', '第五十八章 谁是最幸运的', '6569');
INSERT INTO `books_contents` VALUES ('57', '安徒生童话集.txt', '第五十九章 钟声', '6651');
INSERT INTO `books_contents` VALUES ('58', '安徒生童话集.txt', '第六十章 顽皮的孩子', '6711');
INSERT INTO `books_contents` VALUES ('59', '安徒生童话集.txt', '第六十一章 识字课本', '6755');
INSERT INTO `books_contents` VALUES ('60', '安徒生童话集.txt', '第六十二章 老约翰妮讲的故事', '7011');
INSERT INTO `books_contents` VALUES ('61', '安徒生童话集.txt', '第六十三章 老墓碑', '7303');
INSERT INTO `books_contents` VALUES ('62', '安徒生童话集.txt', '第六十四章 姑妈', '7335');
INSERT INTO `books_contents` VALUES ('63', '安徒生童话集.txt', '第六十五章 墓里的孩子', '7407');
INSERT INTO `books_contents` VALUES ('64', '安徒生童话集.txt', '第六十六章 老路灯', '7483');
INSERT INTO `books_contents` VALUES ('65', '安徒生童话集.txt', '第六十七章 老头子做事总不会错', '7591');
INSERT INTO `books_contents` VALUES ('66', '安徒生童话集.txt', '第六十八章 老房子', '7719');
INSERT INTO `books_contents` VALUES ('67', '安徒生童话集.txt', '第六十九章 天鹅的窠', '7853');
INSERT INTO `books_contents` VALUES ('68', '安徒生童话集.txt', '第七十章 创造', '7911');
INSERT INTO `books_contents` VALUES ('69', '安徒生童话集.txt', '第八十章 冰姑娘', '7979');
INSERT INTO `books_contents` VALUES ('70', '安徒生童话集.txt', '第八十一章 小鬼和小商人', '8775');
INSERT INTO `books_contents` VALUES ('71', '安徒生童话集.txt', '第八十二章 阳光的故事', '8831');
INSERT INTO `books_contents` VALUES ('72', '安徒生童话集.txt', '第八十三章 依卜和小克丽斯玎', '8891');
INSERT INTO `books_contents` VALUES ('73', '安徒生童话集.txt', '第八十四章 梦神①', '9065');
INSERT INTO `books_contents` VALUES ('74', '安徒生童话集.txt', '第八十五章 老上帝还没有灭亡', '9315');
INSERT INTO `books_contents` VALUES ('75', '安徒生童话集.txt', '第八十六章 园丁和他的贵族主人', '9343');
INSERT INTO `books_contents` VALUES ('76', '安徒生童话集.txt', '第八十七章 书法家', '9493');
INSERT INTO `books_contents` VALUES ('77', '安徒生童话集.txt', '第八十八章 茶壶', '9513');
INSERT INTO `books_contents` VALUES ('78', '安徒生童话集.txt', '第八十九章 小小的绿东西', '9531');
INSERT INTO `books_contents` VALUES ('79', '安徒生童话集.txt', '第九十章 一点成绩', '9561');
INSERT INTO `books_contents` VALUES ('80', '安徒生童话集.txt', '第九十一章 天国花园', '9667');
INSERT INTO `books_contents` VALUES ('81', '安徒生童话集.txt', '第九十二章 最难使人相信的事情', '9943');
INSERT INTO `books_contents` VALUES ('82', '安徒生童话集.txt', '第九十三章 一枚银毫', '10049');
INSERT INTO `books_contents` VALUES ('83', '安徒生童话集.txt', '第九十四章 肉肠签子汤', '10121');
INSERT INTO `books_contents` VALUES ('84', '安徒生童话集.txt', '第九十五章 光棍汉的睡帽', '10227');
INSERT INTO `books_contents` VALUES ('85', '安徒生童话集.txt', '第九十六章 做出点样子来', '10367');
INSERT INTO `books_contents` VALUES ('86', '安徒生童话集.txt', '第九十七章 老橡树的最后一梦（一篇圣诞童话）', '10445');
INSERT INTO `books_contents` VALUES ('87', '安徒生童话集.txt', '第九十八章 字母读本', '10517');
INSERT INTO `books_contents` VALUES ('88', '安徒生童话集.txt', '第九十九章 沼泽王的女儿', '10719');
INSERT INTO `books_contents` VALUES ('89', '安徒生童话集.txt', '第一百章 跑得飞快的东西', '11133');
INSERT INTO `books_contents` VALUES ('90', '安徒生童话集.txt', '第一百零一章 钟渊', '11159');
INSERT INTO `books_contents` VALUES ('91', '安徒生童话集.txt', '第一百零二章 狠毒的王子（一个传说）', '11189');
INSERT INTO `books_contents` VALUES ('92', '安徒生童话集.txt', '第一百零三章 踩面包的姑娘', '11347');
INSERT INTO `books_contents` VALUES ('93', '安徒生童话集.txt', '第一百零四章 守塔人奥勒', '11457');
INSERT INTO `books_contents` VALUES ('94', '安徒生童话集.txt', '第一百零五章 安妮·莉丝贝特', '11529');
INSERT INTO `books_contents` VALUES ('95', '安徒生童话集.txt', '第一百零六章 孩子话', '11615');
INSERT INTO `books_contents` VALUES ('96', '安徒生童话集.txt', '第一百零七章 一串珍珠', '11651');
INSERT INTO `books_contents` VALUES ('97', '安徒生童话集.txt', '第一百零八章 墨水笔和墨水瓶', '11755');
INSERT INTO `books_contents` VALUES ('98', '安徒生童话集.txt', '第一百零九章 墓中的孩子', '11781');
INSERT INTO `books_contents` VALUES ('99', '安徒生童话集.txt', '第一百一十章 沙冈那边的一段故事', '11999');
INSERT INTO `books_contents` VALUES ('100', '安徒生童话集.txt', '第一百一十一章 演木偶戏的人', '12419');
INSERT INTO `books_contents` VALUES ('101', '安徒生童话集.txt', '第一百一十二章 两兄弟', '12445');
INSERT INTO `books_contents` VALUES ('102', '安徒生童话集.txt', '第一百一十三章 教堂古钟', '12479');
INSERT INTO `books_contents` VALUES ('103', '安徒生童话集.txt', '第一百一十四章 搭邮车来的十二位', '12527');
INSERT INTO `books_contents` VALUES ('104', '安徒生童话集.txt', '第一百一十五章 屎壳郎', '12617');
INSERT INTO `books_contents` VALUES ('105', '安徒生童话集.txt', '第一百一十六章 老爹做的事总是对的', '12753');
INSERT INTO `books_contents` VALUES ('106', '安徒生童话集.txt', '第一百一十七章 雪人', '12831');
INSERT INTO `books_contents` VALUES ('107', '安徒生童话集.txt', '第一百一十八章 在鸭场里', '12919');
INSERT INTO `books_contents` VALUES ('108', '安徒生童话集.txt', '第一百一十九章 新世纪的缪斯', '13011');
INSERT INTO `books_contents` VALUES ('109', '安徒生童话集.txt', '第一百二十章 冰姑娘', '13137');
INSERT INTO `books_contents` VALUES ('110', '安徒生童话集.txt', '第一百二十一章 蝴蝶', '13685');
INSERT INTO `books_contents` VALUES ('111', '安徒生童话集.txt', '第一百二十二章 普赛克', '13719');
INSERT INTO `books_contents` VALUES ('112', '安徒生童话集.txt', '第一百二十三章 蜗牛和玫瑰树', '13877');
INSERT INTO `books_contents` VALUES ('113', '安徒生童话集.txt', '第一百二十四章 害人鬼进城了', '13921');
INSERT INTO `books_contents` VALUES ('114', '安徒生童话集.txt', '第一百二十五章 风磨', '14081');
INSERT INTO `books_contents` VALUES ('115', '安徒生童话集.txt', '第一百二十六章 银毫子', '14097');
INSERT INTO `books_contents` VALUES ('116', '安徒生童话集.txt', '第一百二十七章 伯尔厄隆的主教和他的亲眷', '14141');
INSERT INTO `books_contents` VALUES ('117', '安徒生童话集.txt', '第一百二十八章 在幼儿室里', '14219');
INSERT INTO `books_contents` VALUES ('118', '安徒生童话集.txt', '第一百二十九章 金宝贝', '14355');
INSERT INTO `books_contents` VALUES ('119', '安徒生童话集.txt', '第一百三十章 狂风吹跑了招牌', '14491');
INSERT INTO `books_contents` VALUES ('120', '安徒生童话集.txt', '第一百三十一章 茶壶', '14533');
INSERT INTO `books_contents` VALUES ('121', '安徒生童话集.txt', '第一百三十二章 民歌的鸟', '14541');
INSERT INTO `books_contents` VALUES ('122', '安徒生童话集.txt', '第一百三十三章 绿色的小东西', '14581');
INSERT INTO `books_contents` VALUES ('123', '安徒生童话集.txt', '第一百三十四章 小精灵和太太', '14605');
INSERT INTO `books_contents` VALUES ('124', '安徒生童话集.txt', '第一百三十五章 贝得、彼得和皮尔', '14671');
INSERT INTO `books_contents` VALUES ('125', '安徒生童话集.txt', '第一百三十六章 隐存着并不就是被忘却', '14715');
INSERT INTO `books_contents` VALUES ('126', '安徒生童话集.txt', '第一百三十七章 看门人的儿子', '14757');
INSERT INTO `books_contents` VALUES ('127', '安徒生童话集.txt', '第一百三十八章 搬迁日', '15043');
INSERT INTO `books_contents` VALUES ('128', '安徒生童话集.txt', '第一百三十九章 谎报夏', '15079');
INSERT INTO `books_contents` VALUES ('129', '安徒生童话集.txt', '第一百四十章 姨妈', '15137');
INSERT INTO `books_contents` VALUES ('130', '安徒生童话集.txt', '第一百四十一章 癞蛤蟆', '15211');
INSERT INTO `books_contents` VALUES ('131', '安徒生童话集.txt', '第一百四十二章 教父的画册', '15323');
INSERT INTO `books_contents` VALUES ('132', '安徒生童话集.txt', '第一百四十三章 碎布块', '15905');
INSERT INTO `books_contents` VALUES ('133', '安徒生童话集.txt', '第一百四十四章 汶岛和格棱岛', '15931');
INSERT INTO `books_contents` VALUES ('134', '安徒生童话集.txt', '第一百四十五章 谁最幸福', '15955');
INSERT INTO `books_contents` VALUES ('135', '安徒生童话集.txt', '第一百四十六章 树精', '16015');
INSERT INTO `books_contents` VALUES ('136', '安徒生童话集.txt', '第一百四十七章 看鸡人格瑞得的一家', '16337');
INSERT INTO `books_contents` VALUES ('137', '安徒生童话集.txt', '第一百四十八章 蓟的经历', '16499');
INSERT INTO `books_contents` VALUES ('138', '安徒生童话集.txt', '第一百四十九章 你能琢磨出什么', '16563');
INSERT INTO `books_contents` VALUES ('139', '安徒生童话集.txt', '第一百五十章 好运气可能在一根签子里', '16619');
INSERT INTO `books_contents` VALUES ('140', '安徒生童话集.txt', '第一百五十一章 彗星', '16661');
INSERT INTO `books_contents` VALUES ('141', '安徒生童话集.txt', '第一百五十二章 一个星期的每一天', '16753');
INSERT INTO `books_contents` VALUES ('142', '安徒生童话集.txt', '第一百五十三章 阳光的故事', '16805');
INSERT INTO `books_contents` VALUES ('143', '安徒生童话集.txt', '第一百五十四章 曾祖父', '16857');
INSERT INTO `books_contents` VALUES ('144', '安徒生童话集.txt', '第一百五十五章 烛', '16919');
INSERT INTO `books_contents` VALUES ('145', '安徒生童话集.txt', '第一百五十六章 最难令人相信的事', '16979');
INSERT INTO `books_contents` VALUES ('146', '安徒生童话集.txt', '第一百五十七章 一家人都怎样说', '17071');
INSERT INTO `books_contents` VALUES ('147', '安徒生童话集.txt', '第一百五十八章 跳吧，舞吧，我的小宝宝', '17099');
INSERT INTO `books_contents` VALUES ('148', '安徒生童话集.txt', '第一百五十九章 大海蟒', '17233');
INSERT INTO `books_contents` VALUES ('149', '安徒生童话集.txt', '第一百六十章 园丁和主人', '17387');
INSERT INTO `books_contents` VALUES ('150', '安徒生童话集.txt', '第一百六十一章 跳蚤和教授', '17501');
INSERT INTO `books_contents` VALUES ('151', '安徒生童话集.txt', '第一百六十二章 老约翰妮讲了些什么', '17579');
INSERT INTO `books_contents` VALUES ('152', '安徒生童话集.txt', '第一百六十三章 大门钥匙', '17829');
INSERT INTO `books_contents` VALUES ('153', '安徒生童话集.txt', '第一百六十四章 跛脚的孩子', '17993');
INSERT INTO `books_contents` VALUES ('154', '安徒生童话集.txt', '第一百六十五章 牙痛姨妈', '18159');
INSERT INTO `books_contents` VALUES ('155', '安徒生童话集.txt', '第一百六十六章 译后记', '18383');
INSERT INTO `books_contents` VALUES ('156', '安徒生童话集.txt', '第一百六十七章 海的女儿', '18409');
INSERT INTO `books_contents` VALUES ('157', '安徒生童话集.txt', '第一百六十八章 邻居们', '18631');
INSERT INTO `books_contents` VALUES ('158', '安徒生童话集.txt', '第一百六十九章 夜莺', '18793');
INSERT INTO `books_contents` VALUES ('1', '傲慢与偏见.txt', '第一章  举世公认的真理', '61');
INSERT INTO `books_contents` VALUES ('2', '傲慢与偏见.txt', '第二章  拜访彬格莱先生', '139');
INSERT INTO `books_contents` VALUES ('3', '傲慢与偏见.txt', '第三章  五个女儿', '209');
INSERT INTO `books_contents` VALUES ('4', '傲慢与偏见.txt', '第四章  赞扬彬格莱先生', '261');
INSERT INTO `books_contents` VALUES ('5', '傲慢与偏见.txt', '第五章  距离浪博恩不远的地方', '301');
INSERT INTO `books_contents` VALUES ('6', '傲慢与偏见.txt', '第六章  拜访尼是斐花园的小姐们', '351');
INSERT INTO `books_contents` VALUES ('7', '傲慢与偏见.txt', '第七章  班纳特先生的全部家当', '471');
INSERT INTO `books_contents` VALUES ('8', '傲慢与偏见.txt', '第八章  五点钟', '567');
INSERT INTO `books_contents` VALUES ('9', '傲慢与偏见.txt', '第九章  伊丽莎白那一晚上', '689');
INSERT INTO `books_contents` VALUES ('10', '傲慢与偏见.txt', '第十章  陪病人几个钟头', '777');
INSERT INTO `books_contents` VALUES ('11', '傲慢与偏见.txt', '第十一章  吃过晚饭以后', '932');
INSERT INTO `books_contents` VALUES ('12', '傲慢与偏见.txt', '第十二章  班纳特姐妹俩的商量', '1014');
INSERT INTO `books_contents` VALUES ('13', '傲慢与偏见.txt', '第十三章  男宾', '1040');
INSERT INTO `books_contents` VALUES ('14', '傲慢与偏见.txt', '第十四章  柯林斯先生1', '1112');
INSERT INTO `books_contents` VALUES ('15', '傲慢与偏见.txt', '第十五章  柯林斯先生2', '1160');
INSERT INTO `books_contents` VALUES ('16', '傲慢与偏见.txt', '第十六章  年轻的小姐们', '1198');
INSERT INTO `books_contents` VALUES ('17', '傲慢与偏见.txt', '第十七章  韦翰先生', '1330');
INSERT INTO `books_contents` VALUES ('18', '傲慢与偏见.txt', '第十八章  尼日斐花园的会客室', '1374');
INSERT INTO `books_contents` VALUES ('19', '傲慢与偏见.txt', '第十九章  柯林斯先生提出求婚', '1543');
INSERT INTO `books_contents` VALUES ('20', '傲慢与偏见.txt', '第二十章  幻想着美满的姻缘', '1599');
INSERT INTO `books_contents` VALUES ('21', '傲慢与偏见.txt', '第二十一章  关于柯林斯先生求婚问题', '1675');
INSERT INTO `books_contents` VALUES ('22', '傲慢与偏见.txt', '第二十二章  卢卡斯府上请去吃饭', '1745');
INSERT INTO `books_contents` VALUES ('23', '傲慢与偏见.txt', '第二十三章  伊丽莎白正跟母亲和姐妹', '1795');
INSERT INTO `books_contents` VALUES ('24', '傲慢与偏见.txt', '第二十四章  彬格莱小姐的信来了', '1857');
INSERT INTO `books_contents` VALUES ('25', '傲慢与偏见.txt', '第二十五章  终于到了星期六', '1931');
INSERT INTO `books_contents` VALUES ('26', '傲慢与偏见.txt', '第二十六章  嘉丁纳太太', '1985');
INSERT INTO `books_contents` VALUES ('27', '傲慢与偏见.txt', '第二十七章  浪搏恩这家人', '2062');
INSERT INTO `books_contents` VALUES ('28', '傲慢与偏见.txt', '第二十八章  旅途上的每一样事物', '2122');
INSERT INTO `books_contents` VALUES ('29', '傲慢与偏见.txt', '第二十九章  罗新斯请客', '2176');
INSERT INTO `books_contents` VALUES ('30', '傲慢与偏见.txt', '第三十章  威廉爵士在汉斯福', '2272');
INSERT INTO `books_contents` VALUES ('31', '傲慢与偏见.txt', '第三十一章  费茨廉的风度', '2310');
INSERT INTO `books_contents` VALUES ('32', '傲慢与偏见.txt', '第三十二章  柯林斯太太和玛丽亚', '2384');
INSERT INTO `books_contents` VALUES ('33', '傲慢与偏见.txt', '第三十三章  伊丽莎白在花园里散步', '2460');
INSERT INTO `books_contents` VALUES ('34', '傲慢与偏见.txt', '第三十四章  柯林斯夫妇走了', '2552');
INSERT INTO `books_contents` VALUES ('35', '傲慢与偏见.txt', '第三十五章  深思默想', '2626');
INSERT INTO `books_contents` VALUES ('36', '傲慢与偏见.txt', '第三十六章  达西先生', '2663');
INSERT INTO `books_contents` VALUES ('37', '傲慢与偏见.txt', '第三十七章  两位先生离开了罗新斯', '2703');
INSERT INTO `books_contents` VALUES ('38', '傲慢与偏见.txt', '第三十八章  和柯林斯先生在饭厅里相遇', '2757');
INSERT INTO `books_contents` VALUES ('39', '傲慢与偏见.txt', '第三十九章  五月的第二个星期', '2799');
INSERT INTO `books_contents` VALUES ('40', '傲慢与偏见.txt', '第四十章  把那桩事告诉吉英', '2865');
INSERT INTO `books_contents` VALUES ('41', '傲慢与偏见.txt', '第四十一章  过了一个星期', '2949');
INSERT INTO `books_contents` VALUES ('42', '傲慢与偏见.txt', '第四十二章  婚姻的幸福', '3039');
INSERT INTO `books_contents` VALUES ('43', '傲慢与偏见.txt', '第四十三章  彭伯里的树林', '3085');
INSERT INTO `books_contents` VALUES ('44', '傲慢与偏见.txt', '第四十四章  达西先生的妹妹', '3260');
INSERT INTO `books_contents` VALUES ('45', '傲慢与偏见.txt', '第四十五章  彬格莱小姐', '3306');
INSERT INTO `books_contents` VALUES ('46', '傲慢与偏见.txt', '第四十六章  蓝白屯', '3358');
INSERT INTO `books_contents` VALUES ('47', '傲慢与偏见.txt', '第四十七章  离开城镇', '3432');
INSERT INTO `books_contents` VALUES ('48', '傲慢与偏见.txt', '第四十八章  等班纳特先生寄信', '3588');
INSERT INTO `books_contents` VALUES ('49', '傲慢与偏见.txt', '第四十九章  矮树林里散步', '3666');
INSERT INTO `books_contents` VALUES ('50', '傲慢与偏见.txt', '第五十章  班纳特先生', '3799');
INSERT INTO `books_contents` VALUES ('51', '傲慢与偏见.txt', '第五十一章  妹妹的婚期到了', '3859');
INSERT INTO `books_contents` VALUES ('52', '傲慢与偏见.txt', '第五十二章  接到了回信', '3943');
INSERT INTO `books_contents` VALUES ('53', '傲慢与偏见.txt', '第五十三章  韦翰先生', '4033');
INSERT INTO `books_contents` VALUES ('54', '傲慢与偏见.txt', '第五十四章  到屋外去溜达', '4167');
INSERT INTO `books_contents` VALUES ('55', '傲慢与偏见.txt', '第五十五章  彬格莱先生又来了', '4247');
INSERT INTO `books_contents` VALUES ('56', '傲慢与偏见.txt', '第五十六章  订婚之后的一个星期', '4369');
INSERT INTO `books_contents` VALUES ('57', '傲慢与偏见.txt', '第五十七章  不速之客', '4542');
INSERT INTO `books_contents` VALUES ('58', '傲慢与偏见.txt', '第五十八章  道歉信', '4606');
INSERT INTO `books_contents` VALUES ('59', '傲慢与偏见.txt', '第五十九章  走进家门', '4712');
INSERT INTO `books_contents` VALUES ('60', '傲慢与偏见.txt', '第六十章  达西先生讲一讲爱上她的经过', '4824');
INSERT INTO `books_contents` VALUES ('61', '傲慢与偏见.txt', '第六十一章  女儿出嫁', '4894');
INSERT INTO `books_contents` VALUES ('1', '巴黎圣母院.txt', '第一卷一     大厅', '17');
INSERT INTO `books_contents` VALUES ('2', '巴黎圣母院.txt', '第一卷二    皮埃尔.格兰古瓦', '288');
INSERT INTO `books_contents` VALUES ('3', '巴黎圣母院.txt', '第一卷三    红衣主教大人', '503');
INSERT INTO `books_contents` VALUES ('4', '巴黎圣母院.txt', '第一卷四    雅克.科珀诺尔君', '541');
INSERT INTO `books_contents` VALUES ('5', '巴黎圣母院.txt', '第一卷五     卡齐莫多', '681');
INSERT INTO `books_contents` VALUES ('6', '巴黎圣母院.txt', '第一卷六    爱斯梅拉达', '839');
INSERT INTO `books_contents` VALUES ('7', '巴黎圣母院.txt', '第二卷一    险情丛生', '895');
INSERT INTO `books_contents` VALUES ('8', '巴黎圣母院.txt', '第二卷二    河滩广场', '927');
INSERT INTO `books_contents` VALUES ('9', '巴黎圣母院.txt', '第二卷三    以吻换揍', '947');
INSERT INTO `books_contents` VALUES ('10', '巴黎圣母院.txt', '第二卷四    夜晚在街上盯梢倩女的种种麻烦', '1109');
INSERT INTO `books_contents` VALUES ('11', '巴黎圣母院.txt', '第二卷五    麻烦接踵而至', '1209');
INSERT INTO `books_contents` VALUES ('12', '巴黎圣母院.txt', '第二卷六    摔破的管子', '1248');
INSERT INTO `books_contents` VALUES ('13', '巴黎圣母院.txt', '第二卷七    新婚之夜', '1595');
INSERT INTO `books_contents` VALUES ('14', '巴黎圣母院.txt', '第三卷一    圣母院', '1834');
INSERT INTO `books_contents` VALUES ('15', '巴黎圣母院.txt', '第三卷二    巴黎鸟瞰', '1891');
INSERT INTO `books_contents` VALUES ('16', '巴黎圣母院.txt', '第四卷一    善良的人们', '1992');
INSERT INTO `books_contents` VALUES ('17', '巴黎圣母院.txt', '第四卷二    克洛德.弗罗洛', '2082');
INSERT INTO `books_contents` VALUES ('18', '巴黎圣母院.txt', '第四卷四    狗与主人', '2184');
INSERT INTO `books_contents` VALUES ('19', '巴黎圣母院.txt', '第四卷五    克洛德.弗罗洛（续）', '2201');
INSERT INTO `books_contents` VALUES ('20', '巴黎圣母院.txt', '第四卷六    不负众望', '2240');
INSERT INTO `books_contents` VALUES ('21', '巴黎圣母院.txt', '第五卷一     圣马丁修道院住持', '2256');
INSERT INTO `books_contents` VALUES ('22', '巴黎圣母院.txt', '第五卷二    这个将毁灭那个', '2461');
INSERT INTO `books_contents` VALUES ('23', '巴黎圣母院.txt', '第六卷一    古时司法公正一瞥', '2552');
INSERT INTO `books_contents` VALUES ('24', '巴黎圣母院.txt', '第六卷二    老鼠洞', '2647');
INSERT INTO `books_contents` VALUES ('25', '巴黎圣母院.txt', '第六卷三    一块玉米饼的故事', '2678');
INSERT INTO `books_contents` VALUES ('26', '巴黎圣母院.txt', '第六卷四    一滴水一滴泪', '2985');
INSERT INTO `books_contents` VALUES ('27', '巴黎圣母院.txt', '第七卷一    给山羊透露秘密的危险', '3150');
INSERT INTO `books_contents` VALUES ('28', '巴黎圣母院.txt', '第七卷二    一个教士和一个哲学家', '3445');
INSERT INTO `books_contents` VALUES ('29', '巴黎圣母院.txt', '第七卷三     大钟', '3581');
INSERT INTO `books_contents` VALUES ('30', '巴黎圣母院.txt', '第七卷四     命运', '3604');
INSERT INTO `books_contents` VALUES ('31', '巴黎圣母院.txt', '第七卷五    两个黑衣人', '3875');
INSERT INTO `books_contents` VALUES ('32', '巴黎圣母院.txt', '第七卷六    户外咒骂可能导致的后果', '3972');
INSERT INTO `books_contents` VALUES ('33', '巴黎圣母院.txt', '第七卷七    野僧', '4090');
INSERT INTO `books_contents` VALUES ('34', '巴黎圣母院.txt', '第七卷八    临河窗子的用处', '4227');
INSERT INTO `books_contents` VALUES ('35', '巴黎圣母院.txt', '第八卷一    金币变枯叶', '4370');
INSERT INTO `books_contents` VALUES ('36', '巴黎圣母院.txt', '第八卷二    金币变枯叶（续）', '4547');
INSERT INTO `books_contents` VALUES ('37', '巴黎圣母院.txt', '第八卷三    金币变枯叶（续完）', '4666');
INSERT INTO `books_contents` VALUES ('38', '巴黎圣母院.txt', '第八卷四    进此处者，抛弃一切希望', '4723');
INSERT INTO `books_contents` VALUES ('39', '巴黎圣母院.txt', '第八卷五    母亲', '4916');
INSERT INTO `books_contents` VALUES ('40', '巴黎圣母院.txt', '第八卷六    三人心不同', '4968');
INSERT INTO `books_contents` VALUES ('41', '巴黎圣母院.txt', '第九卷一    热狂', '5259');
INSERT INTO `books_contents` VALUES ('42', '巴黎圣母院.txt', '第九卷二    驼背.独眼.跛脚', '5376');
INSERT INTO `books_contents` VALUES ('43', '巴黎圣母院.txt', '第九卷三    耳聋', '5423');
INSERT INTO `books_contents` VALUES ('44', '巴黎圣母院.txt', '第九卷四    陶土和水晶', '5472');
INSERT INTO `books_contents` VALUES ('45', '巴黎圣母院.txt', '第九卷五    红门的钥匙', '5627');
INSERT INTO `books_contents` VALUES ('46', '巴黎圣母院.txt', '第九卷六    红门的钥匙（续）', '5655');
INSERT INTO `books_contents` VALUES ('47', '巴黎圣母院.txt', '第十卷一    格兰古瓦妙计连生贝纳尔丹街', '5738');
INSERT INTO `books_contents` VALUES ('48', '巴黎圣母院.txt', '第十卷二    您当流浪汉去吧', '6015');
INSERT INTO `books_contents` VALUES ('49', '巴黎圣母院.txt', '第十卷三    欢乐万岁', '6064');
INSERT INTO `books_contents` VALUES ('50', '巴黎圣母院.txt', '第十卷四    一个帮倒忙的朋友', '6163');
INSERT INTO `books_contents` VALUES ('51', '巴黎圣母院.txt', '第十卷五    法兰西路易大人的祈祷室', '6400');
INSERT INTO `books_contents` VALUES ('52', '巴黎圣母院.txt', '第十卷六    小刀在闲荡', '6981');
INSERT INTO `books_contents` VALUES ('53', '巴黎圣母院.txt', '第十卷七    夏托佩尔援救来了！', '7020');
INSERT INTO `books_contents` VALUES ('54', '巴黎圣母院.txt', '第十一卷一    小鞋', '7051');
INSERT INTO `books_contents` VALUES ('55', '巴黎圣母院.txt', '第十一卷二    美丽的白衣少女', '7579');
INSERT INTO `books_contents` VALUES ('56', '巴黎圣母院.txt', '第十一卷三    福比斯成亲', '7657');
INSERT INTO `books_contents` VALUES ('57', '巴黎圣母院.txt', '第十一卷四     卡齐莫多成亲（完）', '7675');
INSERT INTO `books_contents` VALUES ('1', '百年孤独.txt', '第 一 章  想起父亲带他去参观冰块', '93');
INSERT INTO `books_contents` VALUES ('2', '百年孤独.txt', '第 二 章  曾祖母受了严重的的伤', '775');
INSERT INTO `books_contents` VALUES ('3', '百年孤独.txt', '第 三 章  皮拉·苔列娜的儿子出世以后', '1459');
INSERT INTO `books_contents` VALUES ('4', '百年孤独.txt', '第 四 章  新宅落成', '2335');
INSERT INTO `books_contents` VALUES ('5', '百年孤独.txt', '第 五 章  尼康诺·莱茵纳神父的指示', '3179');
INSERT INTO `books_contents` VALUES ('6', '百年孤独.txt', '第 六 章  奥雷连诺上校', '4041');
INSERT INTO `books_contents` VALUES ('7', '百年孤独.txt', '第 七 章  战争结束了', '4757');
INSERT INTO `books_contents` VALUES ('8', '百年孤独.txt', '第 八 章  阿玛兰塔', '5625');
INSERT INTO `books_contents` VALUES ('9', '百年孤独.txt', '第 九 章  格林列尔多.马克斯上校', '6201');
INSERT INTO `books_contents` VALUES ('10', '百年孤独.txt', '第 十 章  在临终的床上', '7095');
INSERT INTO `books_contents` VALUES ('11', '百年孤独.txt', '第 十 一 章  安慰佩特娜·\r\n\r\n柯特', '7947');
INSERT INTO `books_contents` VALUES ('12', '百年孤独.txt', '第 十 二 章  马孔多居民', '8751');
INSERT INTO `books_contents` VALUES ('13', '百年孤独.txt', '第 十 三 章  教育霍·\r\n\r\n阿卡蒂奥', '9545');
INSERT INTO `books_contents` VALUES ('14', '百年孤独.txt', '第 十 四 章  奥雷连诺上校的丧期', '10423');
INSERT INTO `books_contents` VALUES ('15', '百年孤独.txt', '第 十 五 章  马孔多将', '11289');
INSERT INTO `books_contents` VALUES ('16', '百年孤独.txt', '第 十 六 章  雨，下了四年十一个月零两天', '12135');
INSERT INTO `books_contents` VALUES ('17', '百年孤独.txt', '第 十 七 章  八月里', '12761');
INSERT INTO `books_contents` VALUES ('18', '百年孤独.txt', '第 十 八 章  在梅尔加德斯房间里', '13721');
INSERT INTO `books_contents` VALUES ('19', '百年孤独.txt', '第 十 九 章  阿玛兰塔. 乌苏娜', '14597');
INSERT INTO `books_contents` VALUES ('20', '百年孤独.txt', '第 二 十 章  一个节日的晚上', '15377');
INSERT INTO `books_contents` VALUES ('1', '窗边的小豆豆.txt', '第 1 章  检票口', '28');
INSERT INTO `books_contents` VALUES ('2', '窗边的小豆豆.txt', '第 2 章  电车的玻璃窗', '251');
INSERT INTO `books_contents` VALUES ('3', '窗边的小豆豆.txt', '第 3 章  教室里的座位', '501');
INSERT INTO `books_contents` VALUES ('4', '窗边的小豆豆.txt', '第 4 章  校歌', '699');
INSERT INTO `books_contents` VALUES ('5', '窗边的小豆豆.txt', '第 5 章  单口相声', '819');
INSERT INTO `books_contents` VALUES ('6', '窗边的小豆豆.txt', '第 6 章  脖子上的月票', '1003');
INSERT INTO `books_contents` VALUES ('7', '窗边的小豆豆.txt', '第 7 章  校长小林宗作先生', '1171');
INSERT INTO `books_contents` VALUES ('8', '窗边的小豆豆.txt', '第 8 章  吃饭时不要讲话', '1335');
INSERT INTO `books_contents` VALUES ('9', '窗边的小豆豆.txt', '第 9 章  五分钱', '1505');
INSERT INTO `books_contents` VALUES ('10', '窗边的小豆豆.txt', '第 10 章  边唱边画', '1685');
INSERT INTO `books_contents` VALUES ('11', '窗边的小豆豆.txt', '第 11 章  吃饭歌', '1855');
INSERT INTO `books_contents` VALUES ('12', '窗边的小豆豆.txt', '第 12 章  士兵的眼里涌出了泪花', '2017');
INSERT INTO `books_contents` VALUES ('13', '窗边的小豆豆.txt', '第 13 章  宫崎同学', '2179');
INSERT INTO `books_contents` VALUES ('14', '窗边的小豆豆.txt', '第 14  章  罗基没有了', '2367');
INSERT INTO `books_contents` VALUES ('15', '窗边的小豆豆.txt', '第 15 章  茶话会', '2426');
INSERT INTO `books_contents` VALUES ('16', '窗边的小豆豆.txt', '第 16  章  再见吧，巴学园', '2482');
INSERT INTO `books_contents` VALUES ('1', '动物庄园.txt', '第一章  故事的发生', '23');
INSERT INTO `books_contents` VALUES ('2', '动物庄园.txt', '第二章  老麦哲', '339');
INSERT INTO `books_contents` VALUES ('3', '动物庄园.txt', '第三章  收割牧草', '619');
INSERT INTO `books_contents` VALUES ('4', '动物庄园.txt', '第四章  夏末', '849');
INSERT INTO `books_contents` VALUES ('5', '动物庄园.txt', '第五章  冬天快要到了', '1031');
INSERT INTO `books_contents` VALUES ('6', '动物庄园.txt', '第六章  动物们干活', '1347');
INSERT INTO `books_contents` VALUES ('7', '动物庄园.txt', '第七章  一个寒冷的冬天', '1637');
INSERT INTO `books_contents` VALUES ('8', '动物庄园.txt', '第八章  行刑引起的恐慌', '2019');
INSERT INTO `books_contents` VALUES ('9', '动物庄园.txt', '第九章  鲍克瑟蹄掌上的裂口', '2477');
INSERT INTO `books_contents` VALUES ('10', '动物庄园.txt', '第十章  动物都已相继死去', '2841');
INSERT INTO `books_contents` VALUES ('1', '格林童话.txt', '@青蛙王子', '6');
INSERT INTO `books_contents` VALUES ('2', '格林童话.txt', '@猫和老鼠合伙', '40');
INSERT INTO `books_contents` VALUES ('3', '格林童话.txt', '@圣母的孩子', '46');
INSERT INTO `books_contents` VALUES ('4', '格林童话.txt', '@傻小子学害怕', '56');
INSERT INTO `books_contents` VALUES ('5', '格林童话.txt', '@狼和七只小山羊', '155');
INSERT INTO `books_contents` VALUES ('6', '格林童话.txt', '@忠实的约翰', '168');
INSERT INTO `books_contents` VALUES ('7', '格林童话.txt', '@好交易', '189');
INSERT INTO `books_contents` VALUES ('8', '格林童话.txt', '@令人叫绝的乐师', '202');
INSERT INTO `books_contents` VALUES ('9', '格林童话.txt', '@十二兄弟', '211');
INSERT INTO `books_contents` VALUES ('10', '格林童话.txt', '@一群二流子', '228');
INSERT INTO `books_contents` VALUES ('11', '格林童话.txt', '@小弟弟和小姐姐', '232');
INSERT INTO `books_contents` VALUES ('12', '格林童话.txt', '@莴苣姑娘', '268');
INSERT INTO `books_contents` VALUES ('13', '格林童话.txt', '@森林中的三个小矮人', '287');
INSERT INTO `books_contents` VALUES ('14', '格林童话.txt', '@三个纺纱女', '307');
INSERT INTO `books_contents` VALUES ('15', '格林童话.txt', '@汉赛尔与格莱特', '331');
INSERT INTO `books_contents` VALUES ('16', '格林童话.txt', '@三片蛇叶', '421');
INSERT INTO `books_contents` VALUES ('17', '格林童话.txt', '@白蛇', '429');
INSERT INTO `books_contents` VALUES ('18', '格林童话.txt', '@麦草、煤块和豆子', '444');
INSERT INTO `books_contents` VALUES ('19', '格林童话.txt', '@渔夫和他的妻子', '450');
INSERT INTO `books_contents` VALUES ('20', '格林童话.txt', '@勇敢的小裁缝', '568');
INSERT INTO `books_contents` VALUES ('21', '格林童话.txt', '@灰姑娘', '638');
INSERT INTO `books_contents` VALUES ('22', '格林童话.txt', '@谜语', '699');
INSERT INTO `books_contents` VALUES ('23', '格林童话.txt', '@老鼠、小鸟和香肠', '704');
INSERT INTO `books_contents` VALUES ('24', '格林童话.txt', '@霍勒大妈', '713');
INSERT INTO `books_contents` VALUES ('25', '格林童话.txt', '@七只乌鸦', '740');
INSERT INTO `books_contents` VALUES ('26', '格林童话.txt', '@小红帽', '753');
INSERT INTO `books_contents` VALUES ('27', '格林童话.txt', '@当音乐家去', '787');
INSERT INTO `books_contents` VALUES ('28', '格林童话.txt', '@会唱歌的白骨', '798');
INSERT INTO `books_contents` VALUES ('29', '格林童话.txt', '@魔鬼的三根金发', '816');
INSERT INTO `books_contents` VALUES ('30', '格林童话.txt', '@虱子和跳蚤', '837');
INSERT INTO `books_contents` VALUES ('31', '格林童话.txt', '@没有手的姑娘', '847');
INSERT INTO `books_contents` VALUES ('32', '格林童话.txt', '@称心如意的汉斯', '874');
INSERT INTO `books_contents` VALUES ('33', '格林童话.txt', '@三种语言', '894');
INSERT INTO `books_contents` VALUES ('34', '格林童话.txt', '@聪明的爱尔莎', '903');
INSERT INTO `books_contents` VALUES ('35', '格林童话.txt', '@走进天堂的裁缝', '907');
INSERT INTO `books_contents` VALUES ('36', '格林童话.txt', '@桌子、金驴和棍子', '914');
INSERT INTO `books_contents` VALUES ('37', '格林童话.txt', '@大拇指汤姆', '987');
INSERT INTO `books_contents` VALUES ('38', '格林童话.txt', '@狐狸太太的婚事', '1012');
INSERT INTO `books_contents` VALUES ('39', '格林童话.txt', '@十二个懒汉', '1103');
INSERT INTO `books_contents` VALUES ('40', '格林童话.txt', '@强盗新郎', '1117');
INSERT INTO `books_contents` VALUES ('41', '格林童话.txt', '@海尔·柯贝斯', '1143');
INSERT INTO `books_contents` VALUES ('42', '格林童话.txt', '@教父', '1148');
INSERT INTO `books_contents` VALUES ('43', '格林童话.txt', '@特鲁得太太', '1155');
INSERT INTO `books_contents` VALUES ('44', '格林童话.txt', '@死神教父', '1160');
INSERT INTO `books_contents` VALUES ('45', '格林童话.txt', '@大拇哥游记', '1173');
INSERT INTO `books_contents` VALUES ('46', '格林童话.txt', '@费切尔的怪鸟', '1187');
INSERT INTO `books_contents` VALUES ('47', '格林童话.txt', '@桧树', '1211');
INSERT INTO `books_contents` VALUES ('48', '格林童话.txt', '@老苏丹', '1281');
INSERT INTO `books_contents` VALUES ('49', '格林童话.txt', '@六只天鹅', '1289');
INSERT INTO `books_contents` VALUES ('50', '格林童话.txt', '@玫瑰公主', '1299');
INSERT INTO `books_contents` VALUES ('51', '格林童话.txt', '@鸟弃儿', '1311');
INSERT INTO `books_contents` VALUES ('52', '格林童话.txt', '@画眉嘴国王', '1319');
INSERT INTO `books_contents` VALUES ('53', '格林童话.txt', '@白雪公主', '1370');
INSERT INTO `books_contents` VALUES ('54', '格林童话.txt', '@背囊、帽子和号角', '1428');
INSERT INTO `books_contents` VALUES ('55', '格林童话.txt', '@爱人罗兰', '1443');
INSERT INTO `books_contents` VALUES ('56', '格林童话.txt', '@侏儒妖', '1453');
INSERT INTO `books_contents` VALUES ('57', '格林童话.txt', '@金鸟', '1487');
INSERT INTO `books_contents` VALUES ('58', '格林童话.txt', '@狗和麻雀', '1508');
INSERT INTO `books_contents` VALUES ('59', '格林童话.txt', '@弗雷德里克和凯瑟琳', '1518');
INSERT INTO `books_contents` VALUES ('60', '格林童话.txt', '@两兄弟', '1541');
INSERT INTO `books_contents` VALUES ('61', '格林童话.txt', '@小农夫', '1588');
INSERT INTO `books_contents` VALUES ('62', '格林童话.txt', '@蜂王', '1600');
INSERT INTO `books_contents` VALUES ('63', '格林童话.txt', '@三片羽毛', '1616');
INSERT INTO `books_contents` VALUES ('64', '格林童话.txt', '@金鹅', '1629');
INSERT INTO `books_contents` VALUES ('65', '格林童话.txt', '@千皮兽', '1667');
INSERT INTO `books_contents` VALUES ('66', '格林童话.txt', '@兔子新娘', '1680');
INSERT INTO `books_contents` VALUES ('67', '格林童话.txt', '@十二个猎人', '1688');
INSERT INTO `books_contents` VALUES ('68', '格林童话.txt', '@约丽丹和约雷德尔', '1698');
INSERT INTO `books_contents` VALUES ('69', '格林童话.txt', '@三个幸运儿', '1724');
INSERT INTO `books_contents` VALUES ('70', '格林童话.txt', '@六个人走遍天下', '1731');
INSERT INTO `books_contents` VALUES ('71', '格林童话.txt', '@狼和人', '1747');
INSERT INTO `books_contents` VALUES ('72', '格林童话.txt', '@狼和狐狸', '1751');
INSERT INTO `books_contents` VALUES ('73', '格林童话.txt', '@母狼高司普和狐狸', '1756');
INSERT INTO `books_contents` VALUES ('74', '格林童话.txt', '@狐狸和猫', '1760');
INSERT INTO `books_contents` VALUES ('75', '格林童话.txt', '@石竹花', '1764');
INSERT INTO `books_contents` VALUES ('76', '格林童话.txt', '@聪明的格蕾特', '1773');
INSERT INTO `books_contents` VALUES ('77', '格林童话.txt', '@祖父和孙子', '1779');
INSERT INTO `books_contents` VALUES ('78', '格林童话.txt', '@女水妖', '1787');
INSERT INTO `books_contents` VALUES ('79', '格林童话.txt', '@小母鸡之死', '1791');
INSERT INTO `books_contents` VALUES ('80', '格林童话.txt', '@拉斯廷老兄', '1796');
INSERT INTO `books_contents` VALUES ('81', '格林童话.txt', '@赌鬼汉塞尔', '1814');
INSERT INTO `books_contents` VALUES ('82', '格林童话.txt', '@傻瓜汉斯', '1819');
INSERT INTO `books_contents` VALUES ('83', '格林童话.txt', '@汉斯成亲', '1834');
INSERT INTO `books_contents` VALUES ('84', '格林童话.txt', '@金娃娃', '1839');
INSERT INTO `books_contents` VALUES ('85', '格林童话.txt', '@狐狸和鹅群', '1848');
INSERT INTO `books_contents` VALUES ('86', '格林童话.txt', '@穷人和富人', '1852');
INSERT INTO `books_contents` VALUES ('87', '格林童话.txt', '@少女和狮子', '1866');
INSERT INTO `books_contents` VALUES ('88', '格林童话.txt', '@牧鹅姑娘', '1887');
INSERT INTO `books_contents` VALUES ('89', '格林童话.txt', '@年轻的巨人', '1970');
INSERT INTO `books_contents` VALUES ('90', '格林童话.txt', '@土地神', '1987');
INSERT INTO `books_contents` VALUES ('91', '格林童话.txt', '@金山王', '1993');
INSERT INTO `books_contents` VALUES ('92', '格林童话.txt', '@乌鸦', '2010');
INSERT INTO `books_contents` VALUES ('93', '格林童话.txt', '@聪明的农家女', '2016');
INSERT INTO `books_contents` VALUES ('94', '格林童话.txt', '@老希尔德布朗', '2022');
INSERT INTO `books_contents` VALUES ('95', '格林童话.txt', '@三只小鸟', '2048');
INSERT INTO `books_contents` VALUES ('96', '格林童话.txt', '@生命之水', '2092');
INSERT INTO `books_contents` VALUES ('97', '格林童话.txt', '@万事通大夫', '2109');
INSERT INTO `books_contents` VALUES ('98', '格林童话.txt', '@玻璃瓶中的妖怪', '2113');
INSERT INTO `books_contents` VALUES ('99', '格林童话.txt', '@魔鬼的邋遢兄弟', '2159');
INSERT INTO `books_contents` VALUES ('100', '格林童话.txt', '@熊皮人', '2164');
INSERT INTO `books_contents` VALUES ('101', '格林童话.txt', '@山雀和熊', '2173');
INSERT INTO `books_contents` VALUES ('102', '格林童话.txt', '@甜粥', '2184');
INSERT INTO `books_contents` VALUES ('103', '格林童话.txt', '@聪明的老兄', '2188');
INSERT INTO `books_contents` VALUES ('104', '格林童话.txt', '@蛤蟆的故事', '2195');
INSERT INTO `books_contents` VALUES ('105', '格林童话.txt', '@穷磨房小工和猫', '2205');
INSERT INTO `books_contents` VALUES ('106', '格林童话.txt', '@两个旅行家', '2208');
INSERT INTO `books_contents` VALUES ('107', '格林童话.txt', '@刺猬汉斯', '2234');
INSERT INTO `books_contents` VALUES ('108', '格林童话.txt', '@寿衣', '2246');
INSERT INTO `books_contents` VALUES ('109', '格林童话.txt', '@丛林中的守财奴', '2249');
INSERT INTO `books_contents` VALUES ('110', '格林童话.txt', '@技艺高超的猎人', '2259');
INSERT INTO `books_contents` VALUES ('111', '格林童话.txt', '@来自天堂的连枷', '2263');
INSERT INTO `books_contents` VALUES ('112', '格林童话.txt', '@两个国王的孩子', '2266');
INSERT INTO `books_contents` VALUES ('113', '格林童话.txt', '@聪明的小裁缝', '2279');
INSERT INTO `books_contents` VALUES ('114', '格林童话.txt', '@清白的太阳揭露了真相', '2285');
INSERT INTO `books_contents` VALUES ('115', '格林童话.txt', '@蓝灯', '2289');
INSERT INTO `books_contents` VALUES ('116', '格林童话.txt', '@犟孩子', '2335');
INSERT INTO `books_contents` VALUES ('117', '格林童话.txt', '@三个军医', '2338');
INSERT INTO `books_contents` VALUES ('118', '格林童话.txt', '@七个斯瓦比亚人', '2344');
INSERT INTO `books_contents` VALUES ('119', '格林童话.txt', '@三个小伙计', '2371');
INSERT INTO `books_contents` VALUES ('120', '格林童话.txt', '@魔鬼和他的祖母', '2376');
INSERT INTO `books_contents` VALUES ('121', '格林童话.txt', '@无所畏惧的王子', '2383');
INSERT INTO `books_contents` VALUES ('122', '格林童话.txt', '@魔草', '2393');
INSERT INTO `books_contents` VALUES ('123', '格林童话.txt', '@森林中的老妇人', '2408');
INSERT INTO `books_contents` VALUES ('124', '格林童话.txt', '@三兄弟', '2413');
INSERT INTO `books_contents` VALUES ('125', '格林童话.txt', '@忠实的费迪南和不忠实的费迪南', '2421');
INSERT INTO `books_contents` VALUES ('126', '格林童话.txt', '@铁炉', '2441');
INSERT INTO `books_contents` VALUES ('127', '格林童话.txt', '@懒纺妇', '2461');
INSERT INTO `books_contents` VALUES ('128', '格林童话.txt', '@四个聪明的兄弟', '2473');
INSERT INTO `books_contents` VALUES ('129', '格林童话.txt', '@美丽的卡特琳莱叶和彼夫帕夫波儿特里尔', '2488');
INSERT INTO `books_contents` VALUES ('130', '格林童话.txt', '@一只眼、两只眼和三只眼', '2495');
INSERT INTO `books_contents` VALUES ('131', '格林童话.txt', '@狐狸和马', '2584');
INSERT INTO `books_contents` VALUES ('132', '格林童话.txt', '@十二个跳舞的公主', '2593');
INSERT INTO `books_contents` VALUES ('133', '格林童话.txt', '@六个仆人', '2611');
INSERT INTO `books_contents` VALUES ('134', '格林童话.txt', '@白新娘和黑新娘', '2656');
INSERT INTO `books_contents` VALUES ('135', '格林童话.txt', '@铁汉斯', '2676');
INSERT INTO `books_contents` VALUES ('136', '格林童话.txt', '@三位黑公主', '2687');
INSERT INTO `books_contents` VALUES ('137', '格林童话.txt', '@拉家常', '2693');
INSERT INTO `books_contents` VALUES ('138', '格林童话.txt', '@小羊羔与小鱼儿', '2698');
INSERT INTO `books_contents` VALUES ('139', '格林童话.txt', '@旅行去', '2729');
INSERT INTO `books_contents` VALUES ('140', '格林童话.txt', '@小毛驴', '2735');
INSERT INTO `books_contents` VALUES ('141', '格林童话.txt', '@不肖之子', '2740');
INSERT INTO `books_contents` VALUES ('142', '格林童话.txt', '@萝卜', '2743');
INSERT INTO `books_contents` VALUES ('143', '格林童话.txt', '@返老还童', '2757');
INSERT INTO `books_contents` VALUES ('144', '格林童话.txt', '@上帝的动物和魔鬼的动物', '2760');
INSERT INTO `books_contents` VALUES ('145', '格林童话.txt', '@三个懒汉', '2765');
INSERT INTO `books_contents` VALUES ('146', '格林童话.txt', '@聪明的小牧童', '2768');
INSERT INTO `books_contents` VALUES ('147', '格林童话.txt', '@星星银元', '2772');
INSERT INTO `books_contents` VALUES ('148', '格林童话.txt', '@两枚硬币', '2776');
INSERT INTO `books_contents` VALUES ('149', '格林童话.txt', '@挑媳妇', '2779');
INSERT INTO `books_contents` VALUES ('150', '格林童话.txt', '@扔掉的亚麻', '2782');
INSERT INTO `books_contents` VALUES ('151', '格林童话.txt', '@极乐世界里的故事', '2788');
INSERT INTO `books_contents` VALUES ('152', '格林童话.txt', '@两个神秘的小鞋匠', '2791');
INSERT INTO `books_contents` VALUES ('153', '格林童话.txt', '@迪特马斯的奇谈怪论', '2804');
INSERT INTO `books_contents` VALUES ('154', '格林童话.txt', '@谜语童话', '2808');
INSERT INTO `books_contents` VALUES ('155', '格林童话.txt', '@白雪与红玫', '2811');
INSERT INTO `books_contents` VALUES ('156', '格林童话.txt', '@聪明的小伙计', '2839');
INSERT INTO `books_contents` VALUES ('157', '格林童话.txt', '@水晶棺材', '2843');
INSERT INTO `books_contents` VALUES ('158', '格林童话.txt', '@懒鬼哈利和胖婆特琳娜', '2855');
INSERT INTO `books_contents` VALUES ('159', '格林童话.txt', '@怪鸟格莱弗', '2863');
INSERT INTO `books_contents` VALUES ('160', '格林童话.txt', '@壮士汉斯', '2866');
INSERT INTO `books_contents` VALUES ('161', '格林童话.txt', '@天堂里的农夫', '2874');
INSERT INTO `books_contents` VALUES ('162', '格林童话.txt', '@瘦莉莎', '2877');
INSERT INTO `books_contents` VALUES ('163', '格林童话.txt', '@林中小屋', '2880');
INSERT INTO `books_contents` VALUES ('164', '格林童话.txt', '@同甘共苦', '2922');
INSERT INTO `books_contents` VALUES ('165', '格林童话.txt', '@篱笆国王', '2926');
INSERT INTO `books_contents` VALUES ('166', '格林童话.txt', '@鲽鱼', '2935');
INSERT INTO `books_contents` VALUES ('167', '格林童话.txt', '@鸬鹚和戴胜', '2940');
INSERT INTO `books_contents` VALUES ('168', '格林童话.txt', '@猫头鹰', '2944');
INSERT INTO `books_contents` VALUES ('169', '格林童话.txt', '@月亮', '2948');
INSERT INTO `books_contents` VALUES ('170', '格林童话.txt', '@寿命', '2953');
INSERT INTO `books_contents` VALUES ('171', '格林童话.txt', '@死神的使者', '2958');
INSERT INTO `books_contents` VALUES ('172', '格林童话.txt', '@鞋匠师傅', '2962');
INSERT INTO `books_contents` VALUES ('173', '格林童话.txt', '@井边的牧鹅女', '2967');
INSERT INTO `books_contents` VALUES ('174', '格林童话.txt', '@夏娃的孩子们', '2984');
INSERT INTO `books_contents` VALUES ('175', '格林童话.txt', '@池中水妖', '2987');
INSERT INTO `books_contents` VALUES ('176', '格林童话.txt', '@小人儿的礼物', '2995');
INSERT INTO `books_contents` VALUES ('177', '格林童话.txt', '@巨人和裁缝', '3002');
INSERT INTO `books_contents` VALUES ('178', '格林童话.txt', '@钉子', '3007');
INSERT INTO `books_contents` VALUES ('179', '格林童话.txt', '@坟中的穷少年', '3014');
INSERT INTO `books_contents` VALUES ('180', '格林童话.txt', '@真新娘', '3023');
INSERT INTO `books_contents` VALUES ('181', '格林童话.txt', '@野兔和刺猬', '3048');
INSERT INTO `books_contents` VALUES ('182', '格林童话.txt', '@纺锤、梭子和针', '3061');
INSERT INTO `books_contents` VALUES ('183', '格林童话.txt', '@农夫与魔鬼', '3085');
INSERT INTO `books_contents` VALUES ('184', '格林童话.txt', '@小海兔的故事', '3090');
INSERT INTO `books_contents` VALUES ('185', '格林童话.txt', '@智者神偷', '3100');
INSERT INTO `books_contents` VALUES ('186', '格林童话.txt', '@鼓手', '3112');
INSERT INTO `books_contents` VALUES ('187', '格林童话.txt', '@麦穗的故事', '3211');
INSERT INTO `books_contents` VALUES ('188', '格林童话.txt', '@坟', '3216');
INSERT INTO `books_contents` VALUES ('189', '格林童话.txt', '@老汉伦克朗', '3230');
INSERT INTO `books_contents` VALUES ('190', '格林童话.txt', '@水晶球', '3250');
INSERT INTO `books_contents` VALUES ('191', '格林童话.txt', '@少女玛琳', '3256');
INSERT INTO `books_contents` VALUES ('192', '格林童话.txt', '@牛皮靴', '3300');
INSERT INTO `books_contents` VALUES ('193', '格林童话.txt', '@金钥匙', '3306');
INSERT INTO `books_contents` VALUES ('194', '格林童话.txt', '@森林中的圣约瑟', '3309');
INSERT INTO `books_contents` VALUES ('195', '格林童话.txt', '@十二门徒', '3316');
INSERT INTO `books_contents` VALUES ('196', '格林童话.txt', '@贫穷和谦卑指引天堂之路', '3319');
INSERT INTO `books_contents` VALUES ('197', '格林童话.txt', '@上帝的食物', '3323');
INSERT INTO `books_contents` VALUES ('198', '格林童话.txt', '@三根绿枝', '3326');
INSERT INTO `books_contents` VALUES ('199', '格林童话.txt', '@圣母的小酒杯', '3331');
INSERT INTO `books_contents` VALUES ('200', '格林童话.txt', '@老妈妈', '3334');
INSERT INTO `books_contents` VALUES ('201', '格林童话.txt', '@榛树枝', '3337');
INSERT INTO `books_contents` VALUES ('1', '郭楚海童话.txt', '@宝石星 ', '26');
INSERT INTO `books_contents` VALUES ('2', '郭楚海童话.txt', '@长尾、短尾和黑妮 ', '73');
INSERT INTO `books_contents` VALUES ('3', '郭楚海童话.txt', '@吹牛 ', '223');
INSERT INTO `books_contents` VALUES ('4', '郭楚海童话.txt', '@吹牛大王旅行记 ', '256');
INSERT INTO `books_contents` VALUES ('5', '郭楚海童话.txt', '@吹气恐龙 ', '279');
INSERT INTO `books_contents` VALUES ('6', '郭楚海童话.txt', '@大黑、小黑和细尾巴 ', '398');
INSERT INTO `books_contents` VALUES ('7', '郭楚海童话.txt', '@豆芽失踪记 ', '449');
INSERT INTO `books_contents` VALUES ('8', '郭楚海童话.txt', '@杜杜先生的喷嚏 ', '600');
INSERT INTO `books_contents` VALUES ('9', '郭楚海童话.txt', '@肚子里的两只兔子 ', '625');
INSERT INTO `books_contents` VALUES ('10', '郭楚海童话.txt', '@儿童小诗 ', '662');
INSERT INTO `books_contents` VALUES ('11', '郭楚海童话.txt', '@国王的故事 ', '719');
INSERT INTO `books_contents` VALUES ('12', '郭楚海童话.txt', '@虎王画像 ', '830');
INSERT INTO `books_contents` VALUES ('13', '郭楚海童话.txt', '@灰耳朵的故事 ', '862');
INSERT INTO `books_contents` VALUES ('14', '郭楚海童话.txt', '@灰灰和白白 ', '985');
INSERT INTO `books_contents` VALUES ('15', '郭楚海童话.txt', '@积木城堡 ', '1052');
INSERT INTO `books_contents` VALUES ('16', '郭楚海童话.txt', '@金鱼的故事 ', '1174');
INSERT INTO `books_contents` VALUES ('17', '郭楚海童话.txt', '@老鼠、瓷猫和大花猫 ', '1218');
INSERT INTO `books_contents` VALUES ('18', '郭楚海童话.txt', '@龙的故事 ', '1322');
INSERT INTO `books_contents` VALUES ('19', '郭楚海童话.txt', '@路克打电话 ', '1393');
INSERT INTO `books_contents` VALUES ('20', '郭楚海童话.txt', '@螺旋桨坏了 ', '1472');
INSERT INTO `books_contents` VALUES ('21', '郭楚海童话.txt', '@冒牌米老鼠 ', '1584');
INSERT INTO `books_contents` VALUES ('22', '郭楚海童话.txt', '@莫克和白白 ', '1652');
INSERT INTO `books_contents` VALUES ('23', '郭楚海童话.txt', '@拼图熊猫 ', '1790');
INSERT INTO `books_contents` VALUES ('24', '郭楚海童话.txt', '@鼠王拍戏记 ', '1853');
INSERT INTO `books_contents` VALUES ('25', '郭楚海童话.txt', '@睡觉 ', '1993');
INSERT INTO `books_contents` VALUES ('26', '郭楚海童话.txt', '@说话不算数的下场 ', '2028');
INSERT INTO `books_contents` VALUES ('27', '郭楚海童话.txt', '@铁皮狗失踪记 ', '2058');
INSERT INTO `books_contents` VALUES ('28', '郭楚海童话.txt', '@兔子安哥拉 ', '2175');
INSERT INTO `books_contents` VALUES ('29', '郭楚海童话.txt', '@兔子狼 ', '2266');
INSERT INTO `books_contents` VALUES ('30', '郭楚海童话.txt', '@微型童话四则 ', '2301');
INSERT INTO `books_contents` VALUES ('31', '郭楚海童话.txt', '@西克的故事 ', '2311');
INSERT INTO `books_contents` VALUES ('32', '郭楚海童话.txt', '@西克的和橡皮狗 ', '2452');
INSERT INTO `books_contents` VALUES ('33', '郭楚海童话.txt', '@小白鼠过生日 ', '2546');
INSERT INTO `books_contents` VALUES ('34', '郭楚海童话.txt', '@小黑猫醉酒记 ', '2572');
INSERT INTO `books_contents` VALUES ('35', '郭楚海童话.txt', '@小红帽新传 ', '2715');
INSERT INTO `books_contents` VALUES ('36', '郭楚海童话.txt', '@小乐队 ', '2865');
INSERT INTO `books_contents` VALUES ('37', '郭楚海童话.txt', '@小力克奇遇记 ', '2910');
INSERT INTO `books_contents` VALUES ('38', '郭楚海童话.txt', '@小蜜蜂做好事 ', '2957');
INSERT INTO `books_contents` VALUES ('39', '郭楚海童话.txt', '@一场酒雨 ', '2991');
INSERT INTO `books_contents` VALUES ('40', '郭楚海童话.txt', '@音乐家的故事 ', '3017');
INSERT INTO `books_contents` VALUES ('41', '郭楚海童话.txt', '@隐身药 ', '3056');
INSERT INTO `books_contents` VALUES ('42', '郭楚海童话.txt', '@勇斗野猫 ', '3315');
INSERT INTO `books_contents` VALUES ('43', '郭楚海童话.txt', '@月光宝盒 ', '3404');
INSERT INTO `books_contents` VALUES ('44', '郭楚海童话.txt', '@水晶鞋', '3525');
INSERT INTO `books_contents` VALUES ('1', '活着.txt', '章节：第1节', '15');
INSERT INTO `books_contents` VALUES ('2', '活着.txt', '章节：第2节', '340');
INSERT INTO `books_contents` VALUES ('3', '活着.txt', '章节：第3节', '783');
INSERT INTO `books_contents` VALUES ('4', '活着.txt', '章节：第4节', '1228');
INSERT INTO `books_contents` VALUES ('5', '活着.txt', '章节：第5节', '1659');
INSERT INTO `books_contents` VALUES ('6', '活着.txt', '章节：第6节', '2138');
INSERT INTO `books_contents` VALUES ('7', '活着.txt', '章节：第7节', '2601');
INSERT INTO `books_contents` VALUES ('8', '活着.txt', '章节：第8节', '3092');
INSERT INTO `books_contents` VALUES ('9', '活着.txt', '章节：第9节', '3605');
INSERT INTO `books_contents` VALUES ('10', '活着.txt', '章节：第10节', '3890');
INSERT INTO `books_contents` VALUES ('1', '大提琴手高修.txt', '第一章    故事的开始', '4');
INSERT INTO `books_contents` VALUES ('2', '大提琴手高修.txt', '第二章    高修来到了森林', '71');
INSERT INTO `books_contents` VALUES ('3', '大提琴手高修.txt', '第三章    高修与小狸子', '137');
INSERT INTO `books_contents` VALUES ('4', '大提琴手高修.txt', '第四章    高修与田鼠', '173');
INSERT INTO `books_contents` VALUES ('5', '大提琴手高修.txt', '第五章    演出开始', '220');
INSERT INTO `books_contents` VALUES ('1', '风又三郎.txt', '第一章    九月一日', '5');
INSERT INTO `books_contents` VALUES ('2', '风又三郎.txt', '第二章    九月二日', '147');
INSERT INTO `books_contents` VALUES ('3', '风又三郎.txt', '第三章    九月四日 星期日', '216');
INSERT INTO `books_contents` VALUES ('4', '风又三郎.txt', '第四章    九月五日', '388');
INSERT INTO `books_contents` VALUES ('5', '风又三郎.txt', '第五章    九月七日', '492');
INSERT INTO `books_contents` VALUES ('6', '风又三郎.txt', '第六章    九月八日', '583');
INSERT INTO `books_contents` VALUES ('7', '风又三郎.txt', '第七章    九月十二日 第十二天', '642');
INSERT INTO `books_contents` VALUES ('1', '古斯柯布多力传记.txt', '第一章    森林', '5');
INSERT INTO `books_contents` VALUES ('2', '古斯柯布多力传记.txt', '第二章    天蚕丝工厂', '50');
INSERT INTO `books_contents` VALUES ('3', '古斯柯布多力传记.txt', '第三章    稻田', '118');
INSERT INTO `books_contents` VALUES ('4', '古斯柯布多力传记.txt', '第四章    古伯大博士', '212');
INSERT INTO `books_contents` VALUES ('5', '古斯柯布多力传记.txt', '第五章    伊哈特卜火山局', '269');
INSERT INTO `books_contents` VALUES ('6', '古斯柯布多力传记.txt', '第六章    桑姆特利火山', '292');
INSERT INTO `books_contents` VALUES ('7', '古斯柯布多力传记.txt', '第七章    云海', '356');
INSERT INTO `books_contents` VALUES ('8', '古斯柯布多力传记.txt', '第八章    秋', '387');
INSERT INTO `books_contents` VALUES ('9', '古斯柯布多力传记.txt', '第九章     卡尔保纳多火山', '418');
INSERT INTO `books_contents` VALUES ('1', '银河铁道之夜.txt', '第一章    午后的课', '15');
INSERT INTO `books_contents` VALUES ('2', '银河铁道之夜.txt', '第二章    印刷厂', '39');
INSERT INTO `books_contents` VALUES ('3', '银河铁道之夜.txt', '第三章    家', '56');
INSERT INTO `books_contents` VALUES ('4', '银河铁道之夜.txt', '第四章    半人马星节之夜', '97');
INSERT INTO `books_contents` VALUES ('5', '银河铁道之夜.txt', '第五章    气象标', '136');
INSERT INTO `books_contents` VALUES ('6', '银河铁道之夜.txt', '第六章    银河火车站', '147');
INSERT INTO `books_contents` VALUES ('7', '银河铁道之夜.txt', '第七章    北十字星与普利茅斯海岸', '186');
INSERT INTO `books_contents` VALUES ('8', '银河铁道之夜.txt', '第八章    捕鸟人', '242');
INSERT INTO `books_contents` VALUES ('9', '银河铁道之夜.txt', '第九章    焦班尼的车票', '305');
INSERT INTO `books_contents` VALUES ('1', '白客.txt', '第一章    孔若君功亏一篑', '1');
INSERT INTO `books_contents` VALUES ('2', '白客.txt', '第二章    求职碰壁 ', '163');
INSERT INTO `books_contents` VALUES ('3', '白客.txt', '第三章    18岁生日礼物', '300');
INSERT INTO `books_contents` VALUES ('4', '白客.txt', '第四章    不可思议的一幕', '462');
INSERT INTO `books_contents` VALUES ('5', '白客.txt', '第五章    头号疑案', '649');
INSERT INTO `books_contents` VALUES ('6', '白客.txt', '第六章    雪上加霜', '858');
INSERT INTO `books_contents` VALUES ('7', '白客.txt', '第七章    众志成城突围', '1021');
INSERT INTO `books_contents` VALUES ('8', '白客.txt', '第八章    倒霉的居委会主任', '1231');
INSERT INTO `books_contents` VALUES ('9', '白客.txt', '第九章    白客诞生 ', '1419');
INSERT INTO `books_contents` VALUES ('10', '白客.txt', '第十章    屡试不爽', '1606');
INSERT INTO `books_contents` VALUES ('11', '白客.txt', '第十一章    出人意料的殷静 ', '1744');
INSERT INTO `books_contents` VALUES ('12', '白客.txt', '第十二章    亿万观众目睹异变', '2021');
INSERT INTO `books_contents` VALUES ('13', '白客.txt', '第十三章    转移视线', '2250');
INSERT INTO `books_contents` VALUES ('14', '白客.txt', '第十四章    崔琳披挂上阵 ', '2404');
INSERT INTO `books_contents` VALUES ('15', '白客.txt', '第十五章    取证 ', '2554');
INSERT INTO `books_contents` VALUES ('16', '白客.txt', '第十六章    世纪诉讼之一 ', '2806');
INSERT INTO `books_contents` VALUES ('17', '白客.txt', '第十七章    网恋 ', '3052');
INSERT INTO `books_contents` VALUES ('18', '白客.txt', '第十八章    逼上梁山', '3482');
INSERT INTO `books_contents` VALUES ('19', '白客.txt', '第十九章    杨倪浮出水面', '3668');
INSERT INTO `books_contents` VALUES ('20', '白客.txt', '第二十章    殷静中计', '3853');
INSERT INTO `books_contents` VALUES ('21', '白客.txt', '第二十一章    如梦初醒', '4033');
INSERT INTO `books_contents` VALUES ('22', '白客.txt', '第二十二章    赴约', '4212');
INSERT INTO `books_contents` VALUES ('23', '白客.txt', '第二十三章    殷雪涛的意外发现', '4345');
INSERT INTO `books_contents` VALUES ('24', '白客.txt', '第二十四章    孤注一掷', '4589');
INSERT INTO `books_contents` VALUES ('25', '白客.txt', '第二十五章    不翼而飞', '4790');
INSERT INTO `books_contents` VALUES ('26', '白客.txt', '第二十六章    杨倪被判死刑', '4918');
INSERT INTO `books_contents` VALUES ('27', '白客.txt', '第二十七章    疯狂白客', '5074');
INSERT INTO `books_contents` VALUES ('28', '白客.txt', '第二十八章    结识沈国庆', '5285');
INSERT INTO `books_contents` VALUES ('29', '白客.txt', '第二十九章    夜闯信访办', '5479');
INSERT INTO `books_contents` VALUES ('30', '白客.txt', '第三十章    阿里巴巴悔恨', '5728');
INSERT INTO `books_contents` VALUES ('31', '白客.txt', '第三十一章    走穴淘金', '5902');
INSERT INTO `books_contents` VALUES ('32', '白客.txt', '第三十二章    贾宝玉功绩卓著', '6175');
INSERT INTO `books_contents` VALUES ('33', '白客.txt', '第三十三章    生死搏斗', '6366');
INSERT INTO `books_contents` VALUES ('34', '白客.txt', '第三十四章    结局', '6429');
INSERT INTO `books_contents` VALUES ('1', '金拇指.txt', '第一章    儿子吃狗粮', '1');
INSERT INTO `books_contents` VALUES ('2', '金拇指.txt', '第二章    米小旭的电话', '175');
INSERT INTO `books_contents` VALUES ('3', '金拇指.txt', '第四章   下注赌博', '452');
INSERT INTO `books_contents` VALUES ('4', '金拇指.txt', '第三章    同学聚会', '630');
INSERT INTO `books_contents` VALUES ('5', '金拇指.txt', '第四章    下注赌博', '940');
INSERT INTO `books_contents` VALUES ('6', '金拇指.txt', '第五章    开门红', '1140');
INSERT INTO `books_contents` VALUES ('7', '金拇指.txt', '第六章    一败涂地', '1438');
INSERT INTO `books_contents` VALUES ('8', '金拇指.txt', '第七章    向胡敬求救', '1860');
INSERT INTO `books_contents` VALUES ('9', '金拇指.txt', '第八章    泥沙俱下', '2267');
INSERT INTO `books_contents` VALUES ('10', '金拇指.txt', '第九章    意外发现', '2415');
INSERT INTO `books_contents` VALUES ('11', '金拇指.txt', '第十章    难以置信', '2585');

-- ----------------------------
-- Table structure for `child`
-- ----------------------------
DROP TABLE IF EXISTS `child`;
CREATE TABLE `child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) NOT NULL DEFAULT 'child',
  `grade` varchar(20) NOT NULL DEFAULT '1',
  `sex` varchar(10) NOT NULL,
  `parentPhone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of child
-- ----------------------------
INSERT INTO `child` VALUES ('1', '熊熊', '一年级', '男', '19831127142');
INSERT INTO `child` VALUES ('2', '赵义', '二年级', '女', '19831127142');
INSERT INTO `child` VALUES ('3', '让人', '一年级', '男', '19831127142');
INSERT INTO `child` VALUES ('4', '天天', '三年级', '女', '18730094411');
INSERT INTO `child` VALUES ('5', '糖糖', '二年级', '女', '18831158249');

-- ----------------------------
-- Table structure for `classifyidiom`
-- ----------------------------
DROP TABLE IF EXISTS `classifyidiom`;
CREATE TABLE `classifyidiom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classifyName` varchar(20) NOT NULL,
  `parentId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of classifyidiom
-- ----------------------------
INSERT INTO `classifyidiom` VALUES ('1', '景物', '0');
INSERT INTO `classifyidiom` VALUES ('2', '生肖', '0');
INSERT INTO `classifyidiom` VALUES ('3', '季节', '0');
INSERT INTO `classifyidiom` VALUES ('4', '情绪', '0');
INSERT INTO `classifyidiom` VALUES ('5', '颜色', '0');
INSERT INTO `classifyidiom` VALUES ('6', '大海', '1');
INSERT INTO `classifyidiom` VALUES ('7', '山水', '1');
INSERT INTO `classifyidiom` VALUES ('8', '太阳', '1');
INSERT INTO `classifyidiom` VALUES ('9', '月亮', '1');
INSERT INTO `classifyidiom` VALUES ('10', '花朵', '1');
INSERT INTO `classifyidiom` VALUES ('11', '瀑布', '1');
INSERT INTO `classifyidiom` VALUES ('12', '鼠', '2');
INSERT INTO `classifyidiom` VALUES ('13', '牛', '2');
INSERT INTO `classifyidiom` VALUES ('14', '虎', '2');
INSERT INTO `classifyidiom` VALUES ('15', '兔', '2');
INSERT INTO `classifyidiom` VALUES ('16', '龙', '2');
INSERT INTO `classifyidiom` VALUES ('17', '蛇', '2');
INSERT INTO `classifyidiom` VALUES ('18', '马', '2');
INSERT INTO `classifyidiom` VALUES ('19', '羊', '2');
INSERT INTO `classifyidiom` VALUES ('20', '猴', '2');
INSERT INTO `classifyidiom` VALUES ('21', '鸡', '2');
INSERT INTO `classifyidiom` VALUES ('22', '狗', '2');
INSERT INTO `classifyidiom` VALUES ('23', '猪', '2');
INSERT INTO `classifyidiom` VALUES ('24', '春天', '3');
INSERT INTO `classifyidiom` VALUES ('25', '夏天', '3');
INSERT INTO `classifyidiom` VALUES ('26', '秋天', '3');
INSERT INTO `classifyidiom` VALUES ('27', '冬天', '3');
INSERT INTO `classifyidiom` VALUES ('28', '高兴', '4');
INSERT INTO `classifyidiom` VALUES ('29', '愤怒', '4');
INSERT INTO `classifyidiom` VALUES ('30', '悲伤', '4');
INSERT INTO `classifyidiom` VALUES ('31', '害羞', '4');
INSERT INTO `classifyidiom` VALUES ('32', '紧张', '4');
INSERT INTO `classifyidiom` VALUES ('33', '害怕', '4');
INSERT INTO `classifyidiom` VALUES ('34', '红', '5');
INSERT INTO `classifyidiom` VALUES ('35', '黄', '5');
INSERT INTO `classifyidiom` VALUES ('36', '绿', '5');
INSERT INTO `classifyidiom` VALUES ('37', '蓝', '5');
INSERT INTO `classifyidiom` VALUES ('38', '紫', '5');
INSERT INTO `classifyidiom` VALUES ('39', '黑', '5');
INSERT INTO `classifyidiom` VALUES ('40', '白', '5');

-- ----------------------------
-- Table structure for `collections`
-- ----------------------------
DROP TABLE IF EXISTS `collections`;
CREATE TABLE `collections` (
  `phone_num` varchar(11) NOT NULL,
  `child_name` varchar(20) NOT NULL,
  `collection_type` varchar(20) NOT NULL,
  `collection_content` varchar(100) NOT NULL,
  PRIMARY KEY (`phone_num`,`child_name`,`collection_type`,`collection_content`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collections
-- ----------------------------
INSERT INTO `collections` VALUES ('18831158249', '小明', 'book', '白客');
INSERT INTO `collections` VALUES ('19831127142', '小明', 'book', '动物庄园');
INSERT INTO `collections` VALUES ('19831127142', '小明', 'book', '安娜卡列尼娜');

-- ----------------------------
-- Table structure for `contacts_status`
-- ----------------------------
DROP TABLE IF EXISTS `contacts_status`;
CREATE TABLE `contacts_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_phone` char(11) NOT NULL,
  `to_phone` char(11) NOT NULL,
  `contacts_status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contacts_status
-- ----------------------------
INSERT INTO `contacts_status` VALUES ('1', '18730094411', '18730094412', '0');
INSERT INTO `contacts_status` VALUES ('2', '18730094411', '18730094413', '1');
INSERT INTO `contacts_status` VALUES ('3', '18730094414', '18730094411', '2');
INSERT INTO `contacts_status` VALUES ('4', '18730094415', '18730094411', '1');
INSERT INTO `contacts_status` VALUES ('5', '18730094411', '18730094415', '1');
INSERT INTO `contacts_status` VALUES ('6', '18831158249', '19831127142', '1');
INSERT INTO `contacts_status` VALUES ('7', '18730094411', '18831158249', '1');
INSERT INTO `contacts_status` VALUES ('8', '18730094412', '18831158249', '1');
INSERT INTO `contacts_status` VALUES ('9', '18831158249', '18730094415', '0');

-- ----------------------------
-- Table structure for `daily_recommend`
-- ----------------------------
DROP TABLE IF EXISTS `daily_recommend`;
CREATE TABLE `daily_recommend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `synopsis` varchar(500) NOT NULL,
  `img` varchar(60) NOT NULL,
  `article` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of daily_recommend
-- ----------------------------
INSERT INTO `daily_recommend` VALUES ('1', '    人生的航程，不总是─帆风顺。有风有雨，才能承载生命的厚重；风轻云淡，才适合静静领悟。', 'dailyImg/img1.jpg', '    人生的航程，不总是─帆风顺。有风有雨，才能承载生命的厚重；风轻云淡，才适合静静领悟。喜欢追梦的人，切记不要被梦想主宰。善于谋划的人，切记空想达不到目标。拥有实干精神的人，切记选对方向比努力做事重要。\n');
INSERT INTO `daily_recommend` VALUES ('2', '    快乐带来的智慧存在于清晰的心灵感觉中，不因担心惶恐而困惑、出现盲点。', 'dailyImg/img2.jpg', '    快乐带来的智慧存在于清晰的心灵感觉中，不因担心惶恐而困惑、出现盲点。太阳不会因为你的失意，明天不再升起;月亮不会因为你的抱怨，今晚不再降落。蒙住自己的眼睛，不等于世界就漆黑一团;蒙住别人的眼睛，不等于光明就属于自己!\n');
INSERT INTO `daily_recommend` VALUES ('3', '    天高又怎样，踮起脚尖就更接近阳光。微笑拥抱每一天，做像向日葵般温暖的女孩。', 'dailyImg/img3.jpg', '    天高又怎样，踮起脚尖就更接近阳光。微笑拥抱每一天，做像向日葵般温暖的女孩。以前认为水不可能倒流，那是还没有找到发明抽水机的方法;现在认为太阳不可能从西边出来，这是还没住到太阳从西边出来的星球上。这个世界只有想不到的，没有做不到的!\n');
INSERT INTO `daily_recommend` VALUES ('4', '    路再长也会有终点，夜再长也会有尽头，乌云永远遮不住微笑的太阳!', 'dailyImg/img4.jpg', '    路再长也会有终点，夜再长也会有尽头，乌云永远遮不住微笑的太阳!种子放在水泥地板上会被晒死，种子放在水里会被淹死，种子放到肥沃的土壤里就生根发芽结果。选择决定命运，环境造就人生!\n');
INSERT INTO `daily_recommend` VALUES ('5', '    别让承载负重了心灵，别让琐碎凌乱了平静;以风的执念飞翔，以雨的心胸求索，在平淡中快乐，在精彩中淡然。', 'dailyImg/img5.jpg', '    别让承载负重了心灵，别让琐碎凌乱了平静;以风的执念飞翔，以雨的心胸求索，在平淡中快乐，在精彩中淡然。终而复始，日月是也。死而复生，四时是也。奇正相生，循环无端，涨跌相生，循环无端，涨跌相生，循环无穷。机遇孕育着挑战，挑战中孕育着机遇，这是千古验证了的定律!\n');

-- ----------------------------
-- Table structure for `idiom`
-- ----------------------------
DROP TABLE IF EXISTS `idiom`;
CREATE TABLE `idiom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idiom` char(8) NOT NULL,
  `classification` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=571 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of idiom
-- ----------------------------
INSERT INTO `idiom` VALUES ('1', '八仙过海', '6');
INSERT INTO `idiom` VALUES ('2', '海阔天空', '6');
INSERT INTO `idiom` VALUES ('3', '浩如烟海', '6');
INSERT INTO `idiom` VALUES ('4', '排山倒海', '6');
INSERT INTO `idiom` VALUES ('5', '天涯海角', '6');
INSERT INTO `idiom` VALUES ('6', '海纳百川', '6');
INSERT INTO `idiom` VALUES ('7', '百川归海', '6');
INSERT INTO `idiom` VALUES ('8', '海底捞针', '6');
INSERT INTO `idiom` VALUES ('9', '风平浪静', '6');
INSERT INTO `idiom` VALUES ('10', '一望无际', '6');
INSERT INTO `idiom` VALUES ('11', '汹涌澎湃', '6');
INSERT INTO `idiom` VALUES ('12', '沧海桑田', '6');
INSERT INTO `idiom` VALUES ('13', '沧海一粟', '6');
INSERT INTO `idiom` VALUES ('14', '海市蜃楼', '6');
INSERT INTO `idiom` VALUES ('15', '翻江倒海', '6');
INSERT INTO `idiom` VALUES ('16', '海枯石烂', '6');
INSERT INTO `idiom` VALUES ('17', '水天一色', '6');
INSERT INTO `idiom` VALUES ('18', '波光粼粼', '6');
INSERT INTO `idiom` VALUES ('19', '一碧万顷', '6');
INSERT INTO `idiom` VALUES ('20', '川流不息', '6');
INSERT INTO `idiom` VALUES ('21', '血海深仇', '6');
INSERT INTO `idiom` VALUES ('22', '人山人海', '6');
INSERT INTO `idiom` VALUES ('23', '漂洋过海', '6');
INSERT INTO `idiom` VALUES ('24', '山珍海味', '6');
INSERT INTO `idiom` VALUES ('25', '沧海遗珠', '6');
INSERT INTO `idiom` VALUES ('26', '山清水秀', '7');
INSERT INTO `idiom` VALUES ('27', '千山万水', '7');
INSERT INTO `idiom` VALUES ('28', '湖光山色', '7');
INSERT INTO `idiom` VALUES ('29', '山高水长', '7');
INSERT INTO `idiom` VALUES ('30', '崇山峻岭', '7');
INSERT INTO `idiom` VALUES ('31', '高山流水', '7');
INSERT INTO `idiom` VALUES ('32', '青山绿水', '7');
INSERT INTO `idiom` VALUES ('33', '依山傍水', '7');
INSERT INTO `idiom` VALUES ('34', '锦绣山河', '7');
INSERT INTO `idiom` VALUES ('35', '峰回路转', '7');
INSERT INTO `idiom` VALUES ('36', '细水长流', '7');
INSERT INTO `idiom` VALUES ('37', '白山黑水', '7');
INSERT INTO `idiom` VALUES ('38', '水木清华', '7');
INSERT INTO `idiom` VALUES ('39', '山穷水尽', '7');
INSERT INTO `idiom` VALUES ('40', '恩重如山', '7');
INSERT INTO `idiom` VALUES ('41', '愚公移山', '7');
INSERT INTO `idiom` VALUES ('42', '刀山火海', '7');
INSERT INTO `idiom` VALUES ('43', '烈日炎炎', '8');
INSERT INTO `idiom` VALUES ('44', '骄阳似火', '8');
INSERT INTO `idiom` VALUES ('45', '日薄西山', '8');
INSERT INTO `idiom` VALUES ('46', '日上三竿', '8');
INSERT INTO `idiom` VALUES ('47', '风和日丽', '8');
INSERT INTO `idiom` VALUES ('48', '日月如梭', '8');
INSERT INTO `idiom` VALUES ('49', '拨云见日', '8');
INSERT INTO `idiom` VALUES ('50', '旭日东升', '8');
INSERT INTO `idiom` VALUES ('51', '重见天日', '8');
INSERT INTO `idiom` VALUES ('52', '夸父逐日', '8');
INSERT INTO `idiom` VALUES ('53', '日新月异', '8');
INSERT INTO `idiom` VALUES ('54', '日复一日', '8');
INSERT INTO `idiom` VALUES ('55', '夕阳西下', '8');
INSERT INTO `idiom` VALUES ('56', '日行千里', '8');
INSERT INTO `idiom` VALUES ('57', '暗无天日', '8');
INSERT INTO `idiom` VALUES ('58', '光芒万丈', '8');
INSERT INTO `idiom` VALUES ('59', '日薄桑榆', '8');
INSERT INTO `idiom` VALUES ('60', '日中则昃', '8');
INSERT INTO `idiom` VALUES ('61', '冬日夏云', '8');
INSERT INTO `idiom` VALUES ('62', '干云蔽日', '8');
INSERT INTO `idiom` VALUES ('63', '月黑风高', '9');
INSERT INTO `idiom` VALUES ('64', '风花雪月', '9');
INSERT INTO `idiom` VALUES ('65', '日新月异', '9');
INSERT INTO `idiom` VALUES ('66', '星月交辉', '9');
INSERT INTO `idiom` VALUES ('67', '闭月羞花', '9');
INSERT INTO `idiom` VALUES ('68', '花好月圆', '9');
INSERT INTO `idiom` VALUES ('69', '众星拱月', '9');
INSERT INTO `idiom` VALUES ('70', '水中捞月', '9');
INSERT INTO `idiom` VALUES ('71', '日月星辰', '9');
INSERT INTO `idiom` VALUES ('72', '风光霁月', '9');
INSERT INTO `idiom` VALUES ('73', '披星戴月', '9');
INSERT INTO `idiom` VALUES ('74', '皓月千里', '9');
INSERT INTO `idiom` VALUES ('75', '蹉跎岁月', '9');
INSERT INTO `idiom` VALUES ('76', '猴年马月', '9');
INSERT INTO `idiom` VALUES ('77', '花前月下', '9');
INSERT INTO `idiom` VALUES ('78', '月中折桂', '9');
INSERT INTO `idiom` VALUES ('79', '静影沉璧', '9');
INSERT INTO `idiom` VALUES ('80', '九天揽月', '9');
INSERT INTO `idiom` VALUES ('81', '金枝玉叶', '10');
INSERT INTO `idiom` VALUES ('82', '万紫千红', '10');
INSERT INTO `idiom` VALUES ('83', '落英缤纷', '10');
INSERT INTO `idiom` VALUES ('84', '繁花似锦', '10');
INSERT INTO `idiom` VALUES ('85', '出水芙蓉', '10');
INSERT INTO `idiom` VALUES ('86', '百花齐放', '10');
INSERT INTO `idiom` VALUES ('87', '百花凋零', '10');
INSERT INTO `idiom` VALUES ('88', '含苞待放', '10');
INSERT INTO `idiom` VALUES ('89', '花飞蝶舞', '10');
INSERT INTO `idiom` VALUES ('90', '百花争艳', '10');
INSERT INTO `idiom` VALUES ('91', '鸟语花香', '10');
INSERT INTO `idiom` VALUES ('92', '春暖花开', '10');
INSERT INTO `idiom` VALUES ('93', '似玉如花', '10');
INSERT INTO `idiom` VALUES ('94', '花红柳绿', '10');
INSERT INTO `idiom` VALUES ('95', '春兰秋菊', '10');
INSERT INTO `idiom` VALUES ('96', '兰质蕙心', '10');
INSERT INTO `idiom` VALUES ('97', '桂馥兰香', '10');
INSERT INTO `idiom` VALUES ('98', '五颜六色', '10');
INSERT INTO `idiom` VALUES ('99', '火树银花', '10');
INSERT INTO `idiom` VALUES ('100', '恨紫怨红', '10');
INSERT INTO `idiom` VALUES ('101', '一泻千里', '11');
INSERT INTO `idiom` VALUES ('102', '气势磅礴', '11');
INSERT INTO `idiom` VALUES ('103', '一落千丈', '11');
INSERT INTO `idiom` VALUES ('104', '滔滔不绝', '11');
INSERT INTO `idiom` VALUES ('105', '浩浩荡荡', '11');
INSERT INTO `idiom` VALUES ('106', '汹涌澎湃', '11');
INSERT INTO `idiom` VALUES ('107', '源源不断', '11');
INSERT INTO `idiom` VALUES ('108', '水落石出', '11');
INSERT INTO `idiom` VALUES ('109', '水到渠成', '11');
INSERT INTO `idiom` VALUES ('110', '飞流直下', '11');
INSERT INTO `idiom` VALUES ('111', '龙翔凤翥', '11');
INSERT INTO `idiom` VALUES ('112', '气吞山河', '11');
INSERT INTO `idiom` VALUES ('113', '银河倒泻', '11');
INSERT INTO `idiom` VALUES ('114', '雄伟壮观', '11');
INSERT INTO `idiom` VALUES ('115', '流连忘返', '11');
INSERT INTO `idiom` VALUES ('116', '震耳欲聋', '11');
INSERT INTO `idiom` VALUES ('117', '千丝万缕', '11');
INSERT INTO `idiom` VALUES ('118', '荡气回肠', '11');
INSERT INTO `idiom` VALUES ('119', '波澜壮阔', '11');
INSERT INTO `idiom` VALUES ('120', '烟雾缭绕', '11');
INSERT INTO `idiom` VALUES ('121', '贼眉鼠眼', '12');
INSERT INTO `idiom` VALUES ('122', '投鼠忌器', '12');
INSERT INTO `idiom` VALUES ('123', '无名鼠辈', '12');
INSERT INTO `idiom` VALUES ('124', '鼠目寸光', '12');
INSERT INTO `idiom` VALUES ('125', '首鼠两端', '12');
INSERT INTO `idiom` VALUES ('126', '穷鼠啮狸', '12');
INSERT INTO `idiom` VALUES ('127', '猫鼠同眠', '12');
INSERT INTO `idiom` VALUES ('128', '老鼠过街', '12');
INSERT INTO `idiom` VALUES ('129', '罗雀掘鼠', '12');
INSERT INTO `idiom` VALUES ('130', '胆小如鼠', '12');
INSERT INTO `idiom` VALUES ('131', '抱头鼠窜', '12');
INSERT INTO `idiom` VALUES ('132', '三蛇七鼠', '12');
INSERT INTO `idiom` VALUES ('133', '鼠窃狗偷', '12');
INSERT INTO `idiom` VALUES ('134', '鼠牙雀角', '12');
INSERT INTO `idiom` VALUES ('135', '鼠首偾事', '12');
INSERT INTO `idiom` VALUES ('136', '偃鼠饮河', '12');
INSERT INTO `idiom` VALUES ('137', '两鼠斗穴', '12');
INSERT INTO `idiom` VALUES ('138', '鼠入牛角', '12');
INSERT INTO `idiom` VALUES ('139', '鸱鸦嗜鼠', '12');
INSERT INTO `idiom` VALUES ('140', '雀目鼠步', '12');
INSERT INTO `idiom` VALUES ('141', '行若狐鼠', '12');
INSERT INTO `idiom` VALUES ('142', '蚁溃鼠骇', '12');
INSERT INTO `idiom` VALUES ('143', '稷蜂社鼠', '12');
INSERT INTO `idiom` VALUES ('144', '獐头鼠目', '12');
INSERT INTO `idiom` VALUES ('145', '社鼠城狐', '12');
INSERT INTO `idiom` VALUES ('146', '痴鼠拖姜', '12');
INSERT INTO `idiom` VALUES ('147', '狼奔鼠窜', '12');
INSERT INTO `idiom` VALUES ('148', '狐鼠之徒', '12');
INSERT INTO `idiom` VALUES ('149', '捉鼠拿猫', '12');
INSERT INTO `idiom` VALUES ('150', '官仓老鼠', '12');
INSERT INTO `idiom` VALUES ('151', '问牛知马', '13');
INSERT INTO `idiom` VALUES ('152', '蜗行牛步', '13');
INSERT INTO `idiom` VALUES ('153', '鼠入牛角', '13');
INSERT INTO `idiom` VALUES ('154', '散马休牛', '13');
INSERT INTO `idiom` VALUES ('155', '气冲斗牛', '13');
INSERT INTO `idiom` VALUES ('156', '气吞斗牛', '13');
INSERT INTO `idiom` VALUES ('157', '庖丁解牛', '13');
INSERT INTO `idiom` VALUES ('158', '牛郎织女', '13');
INSERT INTO `idiom` VALUES ('159', '牛毛细雨', '13');
INSERT INTO `idiom` VALUES ('160', '牛刀小试', '13');
INSERT INTO `idiom` VALUES ('161', '泥牛入海', '13');
INSERT INTO `idiom` VALUES ('162', '牛头马面', '13');
INSERT INTO `idiom` VALUES ('163', '牛鬼蛇神', '13');
INSERT INTO `idiom` VALUES ('164', '对牛弹琴', '13');
INSERT INTO `idiom` VALUES ('165', '九牛一毛', '13');
INSERT INTO `idiom` VALUES ('166', '目无全牛', '13');
INSERT INTO `idiom` VALUES ('167', '汗牛充栋', '13');
INSERT INTO `idiom` VALUES ('168', '吴牛喘月', '13');
INSERT INTO `idiom` VALUES ('169', '牵牛下井', '13');
INSERT INTO `idiom` VALUES ('170', '齐王舍牛', '13');
INSERT INTO `idiom` VALUES ('171', '牛衣对泣', '13');
INSERT INTO `idiom` VALUES ('172', '牛骥同皂', '13');
INSERT INTO `idiom` VALUES ('173', '牛溲马勃', '13');
INSERT INTO `idiom` VALUES ('174', '牛黄狗宝', '13');
INSERT INTO `idiom` VALUES ('175', '牛蹄之涔', '13');
INSERT INTO `idiom` VALUES ('176', '牛口之下', '13');
INSERT INTO `idiom` VALUES ('177', '牛农对泣', '13');
INSERT INTO `idiom` VALUES ('178', '老牛破车', '13');
INSERT INTO `idiom` VALUES ('179', '马牛襟裾', '13');
INSERT INTO `idiom` VALUES ('180', '老牛舐犊', '13');
INSERT INTO `idiom` VALUES ('181', '龙争虎斗', '14');
INSERT INTO `idiom` VALUES ('182', '狐假虎威', '14');
INSERT INTO `idiom` VALUES ('183', '龙潭虎穴', '14');
INSERT INTO `idiom` VALUES ('184', '虎视眈眈', '14');
INSERT INTO `idiom` VALUES ('185', '虎头虎脑', '14');
INSERT INTO `idiom` VALUES ('186', '虎头蛇尾', '14');
INSERT INTO `idiom` VALUES ('187', '虎入羊群', '14');
INSERT INTO `idiom` VALUES ('188', '虎背熊腰', '14');
INSERT INTO `idiom` VALUES ('189', '盘龙卧虎', '14');
INSERT INTO `idiom` VALUES ('190', '生龙活虎', '14');
INSERT INTO `idiom` VALUES ('191', '如虎添翼', '14');
INSERT INTO `idiom` VALUES ('192', '狼吞虎咽', '14');
INSERT INTO `idiom` VALUES ('193', '放虎归山', '14');
INSERT INTO `idiom` VALUES ('194', '卧虎藏龙', '14');
INSERT INTO `idiom` VALUES ('195', '养虎为患', '14');
INSERT INTO `idiom` VALUES ('196', '二虎相斗', '14');
INSERT INTO `idiom` VALUES ('197', '为虎作伥', '14');
INSERT INTO `idiom` VALUES ('198', '三人成虎', '14');
INSERT INTO `idiom` VALUES ('199', '画虎刻鹄', '14');
INSERT INTO `idiom` VALUES ('200', '照猫画虎', '14');
INSERT INTO `idiom` VALUES ('201', '狡兔三窟', '15');
INSERT INTO `idiom` VALUES ('202', '兔死狐悲', '15');
INSERT INTO `idiom` VALUES ('203', '动若脱兔', '15');
INSERT INTO `idiom` VALUES ('204', '兔起鹘落', '15');
INSERT INTO `idiom` VALUES ('205', '兔死狗烹', '15');
INSERT INTO `idiom` VALUES ('206', '守株待兔', '15');
INSERT INTO `idiom` VALUES ('207', '一雕双兔', '15');
INSERT INTO `idiom` VALUES ('208', '兔角龟毛', '15');
INSERT INTO `idiom` VALUES ('209', '兔走乌飞', '15');
INSERT INTO `idiom` VALUES ('210', '势若脱兔', '15');
INSERT INTO `idiom` VALUES ('211', '见兔放鹰', '15');
INSERT INTO `idiom` VALUES ('212', '见兔顾犬', '15');
INSERT INTO `idiom` VALUES ('213', '白兔赤乌', '15');
INSERT INTO `idiom` VALUES ('214', '狼奔兔脱', '15');
INSERT INTO `idiom` VALUES ('215', '狐死兔泣', '15');
INSERT INTO `idiom` VALUES ('216', '惊猿脱兔', '15');
INSERT INTO `idiom` VALUES ('217', '得兔忘蹄', '15');
INSERT INTO `idiom` VALUES ('218', '出如脱兔', '15');
INSERT INTO `idiom` VALUES ('219', '全狮搏兔', '15');
INSERT INTO `idiom` VALUES ('220', '兔角牛翼', '15');
INSERT INTO `idiom` VALUES ('221', '龙飞凤舞', '16');
INSERT INTO `idiom` VALUES ('222', '龙争虎斗', '16');
INSERT INTO `idiom` VALUES ('223', '龙马精神', '16');
INSERT INTO `idiom` VALUES ('224', '龙凤呈祥', '16');
INSERT INTO `idiom` VALUES ('225', '龙生九子', '16');
INSERT INTO `idiom` VALUES ('226', '龙潭虎穴', '16');
INSERT INTO `idiom` VALUES ('227', '攀龙附凤', '16');
INSERT INTO `idiom` VALUES ('228', '龙吟虎啸', '16');
INSERT INTO `idiom` VALUES ('229', '龙去鼎湖', '16');
INSERT INTO `idiom` VALUES ('230', '麟凤龟龙', '16');
INSERT INTO `idiom` VALUES ('231', '鱼龙混杂', '16');
INSERT INTO `idiom` VALUES ('232', '车水马龙', '16');
INSERT INTO `idiom` VALUES ('233', '飞龙在天', '16');
INSERT INTO `idiom` VALUES ('234', '蛟龙得水', '16');
INSERT INTO `idiom` VALUES ('235', '蛟龙戏水', '16');
INSERT INTO `idiom` VALUES ('236', '贯斗双龙', '16');
INSERT INTO `idiom` VALUES ('237', '老态龙钟', '16');
INSERT INTO `idiom` VALUES ('238', '笔走龙蛇', '16');
INSERT INTO `idiom` VALUES ('239', '矫若惊龙', '16');
INSERT INTO `idiom` VALUES ('240', '来龙去脉', '16');
INSERT INTO `idiom` VALUES ('241', '掷杖成龙', '16');
INSERT INTO `idiom` VALUES ('242', '望子成龙', '16');
INSERT INTO `idiom` VALUES ('243', '叶公好龙', '16');
INSERT INTO `idiom` VALUES ('244', '乘龙快婿', '16');
INSERT INTO `idiom` VALUES ('245', '画龙点睛', '16');
INSERT INTO `idiom` VALUES ('246', '龙腾虎跃', '16');
INSERT INTO `idiom` VALUES ('247', '龙胡之痛', '16');
INSERT INTO `idiom` VALUES ('248', '龙驭上宾', '16');
INSERT INTO `idiom` VALUES ('249', '龙阳泣鱼', '16');
INSERT INTO `idiom` VALUES ('250', '龙行虎步', '16');
INSERT INTO `idiom` VALUES ('251', '画蛇添足', '17');
INSERT INTO `idiom` VALUES ('252', '蛇蝎心肠', '17');
INSERT INTO `idiom` VALUES ('253', '虎头蛇尾', '17');
INSERT INTO `idiom` VALUES ('254', '杯弓蛇影', '17');
INSERT INTO `idiom` VALUES ('255', '打草惊蛇', '17');
INSERT INTO `idiom` VALUES ('256', '岁在龙蛇', '17');
INSERT INTO `idiom` VALUES ('257', '封豕长蛇', '17');
INSERT INTO `idiom` VALUES ('258', '巴蛇吞象', '17');
INSERT INTO `idiom` VALUES ('259', '养虺成蛇', '17');
INSERT INTO `idiom` VALUES ('260', '佛口蛇心', '17');
INSERT INTO `idiom` VALUES ('261', '鲸吞蛇噬', '17');
INSERT INTO `idiom` VALUES ('262', '飞鸟惊蛇', '17');
INSERT INTO `idiom` VALUES ('263', '蝮蛇螫手', '17');
INSERT INTO `idiom` VALUES ('264', '笔走龙蛇', '17');
INSERT INTO `idiom` VALUES ('265', '牛鬼蛇神', '17');
INSERT INTO `idiom` VALUES ('266', '老马识途', '18');
INSERT INTO `idiom` VALUES ('267', '驷马难追', '18');
INSERT INTO `idiom` VALUES ('268', '车水马龙', '18');
INSERT INTO `idiom` VALUES ('269', '走马观花', '18');
INSERT INTO `idiom` VALUES ('270', '马不停蹄', '18');
INSERT INTO `idiom` VALUES ('271', '鞍马劳顿', '18');
INSERT INTO `idiom` VALUES ('272', '青梅竹马', '18');
INSERT INTO `idiom` VALUES ('273', '见鞍思马', '18');
INSERT INTO `idiom` VALUES ('274', '蛛丝马迹', '18');
INSERT INTO `idiom` VALUES ('275', '盲人瞎马', '18');
INSERT INTO `idiom` VALUES ('276', '汗马功劳', '18');
INSERT INTO `idiom` VALUES ('277', '厉兵秣马', '18');
INSERT INTO `idiom` VALUES ('278', '指鹿为马', '18');
INSERT INTO `idiom` VALUES ('279', '招兵买马', '18');
INSERT INTO `idiom` VALUES ('280', '心猿意马', '18');
INSERT INTO `idiom` VALUES ('281', '悬崖勒马', '18');
INSERT INTO `idiom` VALUES ('282', '害群之马', '18');
INSERT INTO `idiom` VALUES ('283', '塞翁失马', '18');
INSERT INTO `idiom` VALUES ('284', '天马行空', '18');
INSERT INTO `idiom` VALUES ('285', '万马齐喑', '18');
INSERT INTO `idiom` VALUES ('286', '万马奔腾', '18');
INSERT INTO `idiom` VALUES ('287', '千军万马', '18');
INSERT INTO `idiom` VALUES ('288', '倚马可待', '18');
INSERT INTO `idiom` VALUES ('289', '人仰马翻', '18');
INSERT INTO `idiom` VALUES ('290', '一马当先', '18');
INSERT INTO `idiom` VALUES ('291', '亡羊补牢', '19');
INSERT INTO `idiom` VALUES ('292', '顺手牵羊', '19');
INSERT INTO `idiom` VALUES ('293', '羚羊挂角', '19');
INSERT INTO `idiom` VALUES ('294', '羊入虎群', '19');
INSERT INTO `idiom` VALUES ('295', '臧谷亡羊', '19');
INSERT INTO `idiom` VALUES ('296', '羝羊触藩', '19');
INSERT INTO `idiom` VALUES ('297', '羊质虎皮', '19');
INSERT INTO `idiom` VALUES ('298', '羊肠小道', '19');
INSERT INTO `idiom` VALUES ('299', '羊肠九曲', '19');
INSERT INTO `idiom` VALUES ('300', '羊狠狼贪', '19');
INSERT INTO `idiom` VALUES ('301', '牛羊勿践', '19');
INSERT INTO `idiom` VALUES ('302', '商羊鼓舞', '19');
INSERT INTO `idiom` VALUES ('303', '如狼牧羊', '19');
INSERT INTO `idiom` VALUES ('304', '以羊易牛', '19');
INSERT INTO `idiom` VALUES ('305', '使羊将狼', '19');
INSERT INTO `idiom` VALUES ('306', '杀鸡儆猴', '20');
INSERT INTO `idiom` VALUES ('307', '尖嘴猴腮', '20');
INSERT INTO `idiom` VALUES ('308', '猴子捞月', '20');
INSERT INTO `idiom` VALUES ('309', '猴年马月', '20');
INSERT INTO `idiom` VALUES ('310', '轩鹤冠猴', '20');
INSERT INTO `idiom` VALUES ('311', '沐猴而冠', '20');
INSERT INTO `idiom` VALUES ('312', '沐猴冠冕', '20');
INSERT INTO `idiom` VALUES ('313', '杀鸡吓猴', '20');
INSERT INTO `idiom` VALUES ('314', '弄鬼掉猴', '20');
INSERT INTO `idiom` VALUES ('315', '猴头猴脑', '20');
INSERT INTO `idiom` VALUES ('316', '小肚鸡肠', '21');
INSERT INTO `idiom` VALUES ('317', '鸡毛蒜皮', '21');
INSERT INTO `idiom` VALUES ('318', '鹤立鸡群', '21');
INSERT INTO `idiom` VALUES ('319', '鸡鸣狗盗', '21');
INSERT INTO `idiom` VALUES ('320', '闻鸡起舞', '21');
INSERT INTO `idiom` VALUES ('321', '鸡飞蛋打', '21');
INSERT INTO `idiom` VALUES ('322', '鸡犬不宁', '21');
INSERT INTO `idiom` VALUES ('323', '金鸡独立', '21');
INSERT INTO `idiom` VALUES ('324', '呆若木鸡', '21');
INSERT INTO `idiom` VALUES ('325', '偷鸡摸狗', '21');
INSERT INTO `idiom` VALUES ('326', '鸡鸣狗盗', '22');
INSERT INTO `idiom` VALUES ('327', '蝇营狗苟', '22');
INSERT INTO `idiom` VALUES ('328', '狼心狗肺', '22');
INSERT INTO `idiom` VALUES ('329', '狗血淋头', '22');
INSERT INTO `idiom` VALUES ('330', '狗拿耗子', '22');
INSERT INTO `idiom` VALUES ('331', '狗尾续貂', '22');
INSERT INTO `idiom` VALUES ('332', '狐朋狗友', '22');
INSERT INTO `idiom` VALUES ('333', '狗仗人势', '22');
INSERT INTO `idiom` VALUES ('334', '烹狗藏弓', '22');
INSERT INTO `idiom` VALUES ('335', '偷鸡摸狗', '22');
INSERT INTO `idiom` VALUES ('336', '关门打狗', '22');
INSERT INTO `idiom` VALUES ('337', '人面狗心', '22');
INSERT INTO `idiom` VALUES ('338', '兔死狗烹', '22');
INSERT INTO `idiom` VALUES ('339', '指鸡骂狗', '22');
INSERT INTO `idiom` VALUES ('340', '狗盗鼠窃', '22');
INSERT INTO `idiom` VALUES ('341', '狗急跳墙', '22');
INSERT INTO `idiom` VALUES ('342', '狗吠非主', '22');
INSERT INTO `idiom` VALUES ('343', '狗吠不惊', '22');
INSERT INTO `idiom` VALUES ('344', '嫁狗逐狗', '22');
INSERT INTO `idiom` VALUES ('345', '泥猪疥狗', '22');
INSERT INTO `idiom` VALUES ('346', '猪狗不如', '23');
INSERT INTO `idiom` VALUES ('347', '猪狗朋友', '23');
INSERT INTO `idiom` VALUES ('348', '猪卑狗险', '23');
INSERT INTO `idiom` VALUES ('349', '一龙一猪', '23');
INSERT INTO `idiom` VALUES ('350', '泥猪瓦狗', '23');
INSERT INTO `idiom` VALUES ('351', '泥猪疥狗', '23');
INSERT INTO `idiom` VALUES ('352', '指猪骂狗', '23');
INSERT INTO `idiom` VALUES ('353', '猪突豨勇', '23');
INSERT INTO `idiom` VALUES ('354', '肥猪拱门', '23');
INSERT INTO `idiom` VALUES ('355', '泥猪瓦狗', '23');
INSERT INTO `idiom` VALUES ('356', '牧猪奴戏', '23');
INSERT INTO `idiom` VALUES ('357', '春暖花开', '24');
INSERT INTO `idiom` VALUES ('358', '春色满园', '24');
INSERT INTO `idiom` VALUES ('359', '草长莺飞', '24');
INSERT INTO `idiom` VALUES ('360', '莺歌燕舞', '24');
INSERT INTO `idiom` VALUES ('361', '鸟语花香', '24');
INSERT INTO `idiom` VALUES ('362', '生机勃勃', '24');
INSERT INTO `idiom` VALUES ('363', '春回大地', '24');
INSERT INTO `idiom` VALUES ('364', '万物复苏', '24');
INSERT INTO `idiom` VALUES ('365', '欣欣向荣', '24');
INSERT INTO `idiom` VALUES ('366', '万紫千红', '24');
INSERT INTO `idiom` VALUES ('367', '百花齐放', '24');
INSERT INTO `idiom` VALUES ('368', '春和景明', '24');
INSERT INTO `idiom` VALUES ('369', '春风化雨', '24');
INSERT INTO `idiom` VALUES ('370', '姹紫嫣红', '24');
INSERT INTO `idiom` VALUES ('371', '绿草如茵', '24');
INSERT INTO `idiom` VALUES ('372', '花红柳绿', '24');
INSERT INTO `idiom` VALUES ('373', '柳暗花明', '24');
INSERT INTO `idiom` VALUES ('374', '烈日炎炎', '25');
INSERT INTO `idiom` VALUES ('375', '流金砾石', '25');
INSERT INTO `idiom` VALUES ('376', '冬温夏清', '25');
INSERT INTO `idiom` VALUES ('377', '七月流火', '25');
INSERT INTO `idiom` VALUES ('378', '无冬无夏', '25');
INSERT INTO `idiom` VALUES ('379', '春生夏长', '25');
INSERT INTO `idiom` VALUES ('380', '春风夏雨', '25');
INSERT INTO `idiom` VALUES ('381', '一叶知秋', '26');
INSERT INTO `idiom` VALUES ('382', '春华秋实', '26');
INSERT INTO `idiom` VALUES ('383', '五谷丰登', '26');
INSERT INTO `idiom` VALUES ('384', '落叶知秋', '26');
INSERT INTO `idiom` VALUES ('385', '平分秋色', '26');
INSERT INTO `idiom` VALUES ('386', '明察秋毫', '26');
INSERT INTO `idiom` VALUES ('387', '望穿秋水', '26');
INSERT INTO `idiom` VALUES ('388', '暗送秋波', '26');
INSERT INTO `idiom` VALUES ('389', '春兰秋菊', '26');
INSERT INTO `idiom` VALUES ('390', '多事之秋', '26');
INSERT INTO `idiom` VALUES ('391', '白雪皑皑', '27');
INSERT INTO `idiom` VALUES ('392', '雪中送炭', '27');
INSERT INTO `idiom` VALUES ('393', '雪上加霜', '27');
INSERT INTO `idiom` VALUES ('394', '阳春白雪', '27');
INSERT INTO `idiom` VALUES ('395', '银装素裹', '27');
INSERT INTO `idiom` VALUES ('396', '程门立雪', '27');
INSERT INTO `idiom` VALUES ('397', '玉树银花', '27');
INSERT INTO `idiom` VALUES ('398', '三冬二夏', '27');
INSERT INTO `idiom` VALUES ('399', '雪兆丰年', '27');
INSERT INTO `idiom` VALUES ('400', '无冬无夏', '27');
INSERT INTO `idiom` VALUES ('401', '喜上眉梢', '28');
INSERT INTO `idiom` VALUES ('402', '喜出望外', '28');
INSERT INTO `idiom` VALUES ('403', '欢天喜地', '28');
INSERT INTO `idiom` VALUES ('404', '心花怒放', '28');
INSERT INTO `idiom` VALUES ('405', '眉开眼笑', '28');
INSERT INTO `idiom` VALUES ('406', '神采飞扬', '28');
INSERT INTO `idiom` VALUES ('407', '捧腹大笑', '28');
INSERT INTO `idiom` VALUES ('408', '欢呼雀跃', '28');
INSERT INTO `idiom` VALUES ('409', '扬眉吐气', '28');
INSERT INTO `idiom` VALUES ('410', '乐不思蜀', '28');
INSERT INTO `idiom` VALUES ('411', '怒火中烧', '29');
INSERT INTO `idiom` VALUES ('412', '怒发冲冠', '29');
INSERT INTO `idiom` VALUES ('413', '大发雷霆', '29');
INSERT INTO `idiom` VALUES ('414', '暴跳如雷', '29');
INSERT INTO `idiom` VALUES ('415', '火冒三丈', '29');
INSERT INTO `idiom` VALUES ('416', '怒不可遏', '29');
INSERT INTO `idiom` VALUES ('417', '咬牙切齿', '29');
INSERT INTO `idiom` VALUES ('418', '拂袖而去', '29');
INSERT INTO `idiom` VALUES ('419', '恼羞成怒', '29');
INSERT INTO `idiom` VALUES ('420', '冲冠眦裂', '29');
INSERT INTO `idiom` VALUES ('421', '泣不成声', '30');
INSERT INTO `idiom` VALUES ('422', '悲痛欲绝', '30');
INSERT INTO `idiom` VALUES ('423', '撕心裂肺', '30');
INSERT INTO `idiom` VALUES ('424', '悲不自胜', '30');
INSERT INTO `idiom` VALUES ('425', '郁郁寡欢', '30');
INSERT INTO `idiom` VALUES ('426', '黯然销魂', '30');
INSERT INTO `idiom` VALUES ('427', '人琴俱亡', '30');
INSERT INTO `idiom` VALUES ('428', '痛不欲生', '30');
INSERT INTO `idiom` VALUES ('429', '心如刀割', '30');
INSERT INTO `idiom` VALUES ('430', '如丧考妣', '30');
INSERT INTO `idiom` VALUES ('431', '面红耳赤', '31');
INSERT INTO `idiom` VALUES ('432', '手足无措', '31');
INSERT INTO `idiom` VALUES ('433', '羞人答答', '31');
INSERT INTO `idiom` VALUES ('434', '忸怩不安', '31');
INSERT INTO `idiom` VALUES ('435', '扭扭捏捏', '31');
INSERT INTO `idiom` VALUES ('436', '烟视媚行', '31');
INSERT INTO `idiom` VALUES ('437', '怯声怯气', '31');
INSERT INTO `idiom` VALUES ('438', '业业矜矜', '31');
INSERT INTO `idiom` VALUES ('439', '正襟危坐', '31');
INSERT INTO `idiom` VALUES ('440', '桃羞杏让', '31');
INSERT INTO `idiom` VALUES ('441', '忐忑不安', '32');
INSERT INTO `idiom` VALUES ('442', '坐立不安', '32');
INSERT INTO `idiom` VALUES ('443', '如坐针毡', '32');
INSERT INTO `idiom` VALUES ('444', '惊弓之鸟', '32');
INSERT INTO `idiom` VALUES ('445', '惴惴不安', '32');
INSERT INTO `idiom` VALUES ('446', '七上八下', '32');
INSERT INTO `idiom` VALUES ('447', '魂不守舍', '32');
INSERT INTO `idiom` VALUES ('448', '如履薄冰', '32');
INSERT INTO `idiom` VALUES ('449', '不知所措', '32');
INSERT INTO `idiom` VALUES ('450', '谈虎色变', '32');
INSERT INTO `idiom` VALUES ('451', '心有余悸', '33');
INSERT INTO `idiom` VALUES ('452', '胆战心惊', '33');
INSERT INTO `idiom` VALUES ('453', '望而生畏', '33');
INSERT INTO `idiom` VALUES ('454', '毛骨悚然', '33');
INSERT INTO `idiom` VALUES ('455', '不寒而栗', '33');
INSERT INTO `idiom` VALUES ('456', '战战兢兢', '33');
INSERT INTO `idiom` VALUES ('457', '栗栗危惧', '33');
INSERT INTO `idiom` VALUES ('458', '诚惶诚恐', '33');
INSERT INTO `idiom` VALUES ('459', '惶恐不安', '33');
INSERT INTO `idiom` VALUES ('460', '骇人听闻', '33');
INSERT INTO `idiom` VALUES ('461', '花红柳绿', '34');
INSERT INTO `idiom` VALUES ('462', '面红耳赤', '34');
INSERT INTO `idiom` VALUES ('463', '唇红齿白', '34');
INSERT INTO `idiom` VALUES ('464', '姹紫嫣红', '34');
INSERT INTO `idiom` VALUES ('465', '绿肥红瘦', '34');
INSERT INTO `idiom` VALUES ('466', '红杏出墙', '34');
INSERT INTO `idiom` VALUES ('467', '绵绵红光', '34');
INSERT INTO `idiom` VALUES ('468', '灯红酒绿', '34');
INSERT INTO `idiom` VALUES ('469', '暮翠朝红', '34');
INSERT INTO `idiom` VALUES ('470', '大红大紫', '34');
INSERT INTO `idiom` VALUES ('471', '万紫千红', '34');
INSERT INTO `idiom` VALUES ('472', '红愁绿惨', '34');
INSERT INTO `idiom` VALUES ('473', '红叶之题', '34');
INSERT INTO `idiom` VALUES ('474', '红尘客梦', '34');
INSERT INTO `idiom` VALUES ('475', '看破红尘', '34');
INSERT INTO `idiom` VALUES ('476', '白发红颜', '34');
INSERT INTO `idiom` VALUES ('477', '倚翠偎红', '34');
INSERT INTO `idiom` VALUES ('478', '驻红却白', '34');
INSERT INTO `idiom` VALUES ('479', '露红烟紫', '34');
INSERT INTO `idiom` VALUES ('480', '绿暗红稀', '34');
INSERT INTO `idiom` VALUES ('481', '黄发垂髫', '35');
INSERT INTO `idiom` VALUES ('482', '信口雌黄', '35');
INSERT INTO `idiom` VALUES ('483', '一抔黄土', '35');
INSERT INTO `idiom` VALUES ('484', '一枕黄粱', '35');
INSERT INTO `idiom` VALUES ('485', '东门黄犬', '35');
INSERT INTO `idiom` VALUES ('486', '人老珠黄', '35');
INSERT INTO `idiom` VALUES ('487', '抱恨黄泉', '35');
INSERT INTO `idiom` VALUES ('488', '明日黄花', '35');
INSERT INTO `idiom` VALUES ('489', '杳如黄鹤', '35');
INSERT INTO `idiom` VALUES ('490', '牛黄狗宝', '35');
INSERT INTO `idiom` VALUES ('491', '碧落黄泉', '35');
INSERT INTO `idiom` VALUES ('492', '飞黄腾达', '35');
INSERT INTO `idiom` VALUES ('493', '青灯黄卷', '35');
INSERT INTO `idiom` VALUES ('494', '青黄沟木', '35');
INSERT INTO `idiom` VALUES ('495', '面黄肌瘦', '35');
INSERT INTO `idiom` VALUES ('496', '魏紫姚黄', '35');
INSERT INTO `idiom` VALUES ('497', '黄杨厄闰', '35');
INSERT INTO `idiom` VALUES ('498', '黄河水清', '35');
INSERT INTO `idiom` VALUES ('499', '青山绿水', '36');
INSERT INTO `idiom` VALUES ('500', '花红柳绿', '36');
INSERT INTO `idiom` VALUES ('501', '暗绿稀红', '36');
INSERT INTO `idiom` VALUES ('502', '惨绿愁红', '36');
INSERT INTO `idiom` VALUES ('503', '惨绿少年', '36');
INSERT INTO `idiom` VALUES ('504', '愁红怨绿', '36');
INSERT INTO `idiom` VALUES ('505', '施绯拖绿', '36');
INSERT INTO `idiom` VALUES ('506', '灯红酒绿', '36');
INSERT INTO `idiom` VALUES ('507', '脱白挂绿', '36');
INSERT INTO `idiom` VALUES ('508', '绿鬓朱颜', '36');
INSERT INTO `idiom` VALUES ('509', '绿草如茵', '36');
INSERT INTO `idiom` VALUES ('510', '绿肥红瘦', '36');
INSERT INTO `idiom` VALUES ('511', '青出于蓝', '37');
INSERT INTO `idiom` VALUES ('512', '筚路蓝缕', '37');
INSERT INTO `idiom` VALUES ('513', '衣冠蓝缕', '37');
INSERT INTO `idiom` VALUES ('514', '白袷蓝衫', '37');
INSERT INTO `idiom` VALUES ('515', '而胜于蓝', '37');
INSERT INTO `idiom` VALUES ('516', '染蓝涅皂', '37');
INSERT INTO `idiom` VALUES ('517', '蓝田生玉', '37');
INSERT INTO `idiom` VALUES ('518', '姹紫嫣红', '38');
INSERT INTO `idiom` VALUES ('519', '万紫千红', '38');
INSERT INTO `idiom` VALUES ('520', '佩紫怀黄', '38');
INSERT INTO `idiom` VALUES ('521', '俯拾青紫', '38');
INSERT INTO `idiom` VALUES ('522', '以紫乱朱', '38');
INSERT INTO `idiom` VALUES ('523', '兼朱重紫', '38');
INSERT INTO `idiom` VALUES ('524', '大红大紫', '38');
INSERT INTO `idiom` VALUES ('525', '姚黄魏紫', '38');
INSERT INTO `idiom` VALUES ('526', '带金佩紫', '38');
INSERT INTO `idiom` VALUES ('527', '怀银纡紫', '38');
INSERT INTO `idiom` VALUES ('528', '恶紫夺朱', '38');
INSERT INTO `idiom` VALUES ('529', '拖青纡紫', '38');
INSERT INTO `idiom` VALUES ('530', '掇青拾紫', '38');
INSERT INTO `idiom` VALUES ('531', '朱紫难别', '38');
INSERT INTO `idiom` VALUES ('532', '拖金委紫', '38');
INSERT INTO `idiom` VALUES ('533', '父紫儿朱', '38');
INSERT INTO `idiom` VALUES ('534', '红紫乱朱', '38');
INSERT INTO `idiom` VALUES ('535', '衣紫腰银', '38');
INSERT INTO `idiom` VALUES ('536', '颠倒黑白', '39');
INSERT INTO `idiom` VALUES ('537', '判若黑白', '39');
INSERT INTO `idiom` VALUES ('538', '与之俱黑', '39');
INSERT INTO `idiom` VALUES ('539', '数黄道黑', '39');
INSERT INTO `idiom` VALUES ('540', '昏天黑地', '39');
INSERT INTO `idiom` VALUES ('541', '数黑论黄', '39');
INSERT INTO `idiom` VALUES ('542', '月黑风高', '39');
INSERT INTO `idiom` VALUES ('543', '混淆黑白', '39');
INSERT INTO `idiom` VALUES ('544', '白水黑山', '39');
INSERT INTO `idiom` VALUES ('545', '黑白不分', '39');
INSERT INTO `idiom` VALUES ('546', '知白守黑', '39');
INSERT INTO `idiom` VALUES ('547', '粉白黛黑', '39');
INSERT INTO `idiom` VALUES ('548', '知白守黑', '39');
INSERT INTO `idiom` VALUES ('549', '近墨者黑', '39');
INSERT INTO `idiom` VALUES ('550', '食亲财黑', '39');
INSERT INTO `idiom` VALUES ('551', '一清二白', '40');
INSERT INTO `idiom` VALUES ('552', '白驹过隙', '40');
INSERT INTO `idiom` VALUES ('553', '一穷二白', '40');
INSERT INTO `idiom` VALUES ('554', '三复白圭', '40');
INSERT INTO `idiom` VALUES ('555', '不明不白', '40');
INSERT INTO `idiom` VALUES ('556', '判若黑白', '40');
INSERT INTO `idiom` VALUES ('557', '如白染皂', '40');
INSERT INTO `idiom` VALUES ('558', '平白无故', '40');
INSERT INTO `idiom` VALUES ('559', '心贯白日', '40');
INSERT INTO `idiom` VALUES ('560', '明明白白', '40');
INSERT INTO `idiom` VALUES ('561', '月白风清', '40');
INSERT INTO `idiom` VALUES ('562', '李白桃红', '40');
INSERT INTO `idiom` VALUES ('563', '望断白云', '40');
INSERT INTO `idiom` VALUES ('564', '洁白无瑕', '40');
INSERT INTO `idiom` VALUES ('565', '颠倒黑白', '40');
INSERT INTO `idiom` VALUES ('566', '白头偕老', '40');
INSERT INTO `idiom` VALUES ('567', '白手起家', '40');
INSERT INTO `idiom` VALUES ('568', '白日做梦', '40');
INSERT INTO `idiom` VALUES ('569', '白水艰辛', '40');
INSERT INTO `idiom` VALUES ('570', '白虹贯日', '40');

-- ----------------------------
-- Table structure for `idiom_save`
-- ----------------------------
DROP TABLE IF EXISTS `idiom_save`;
CREATE TABLE `idiom_save` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` char(11) NOT NULL,
  `childName` varchar(20) NOT NULL,
  `idiomName` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of idiom_save
-- ----------------------------
INSERT INTO `idiom_save` VALUES ('1', '18730094411', '小明', '攀龙附凤');
INSERT INTO `idiom_save` VALUES ('2', '18831158249', '糖糖', '海阔天空');
INSERT INTO `idiom_save` VALUES ('3', '18730094411', '小明', '龙飞凤舞');
INSERT INTO `idiom_save` VALUES ('4', '18730094411', '小明', '龙生九子');
INSERT INTO `idiom_save` VALUES ('5', '18730094411', '小明', '麟凤龟龙');
INSERT INTO `idiom_save` VALUES ('6', '18730094411', '天天', '春暖花开');
INSERT INTO `idiom_save` VALUES ('7', '18730094412', '天天', '金枝玉叶');
INSERT INTO `idiom_save` VALUES ('8', '18831158249', '糖糖', '与世无争');

-- ----------------------------
-- Table structure for `idiom_search_history`
-- ----------------------------
DROP TABLE IF EXISTS `idiom_search_history`;
CREATE TABLE `idiom_search_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` char(11) NOT NULL,
  `childName` varchar(20) NOT NULL,
  `searchStr` varchar(50) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of idiom_search_history
-- ----------------------------
INSERT INTO `idiom_search_history` VALUES ('1', '18730094411', '天天', '五湖四海', '1');
INSERT INTO `idiom_search_history` VALUES ('2', '18730094411', '天天', '九洲四海', '1');
INSERT INTO `idiom_search_history` VALUES ('3', '18730094411', '天天', '一丁点儿', '1');
INSERT INTO `idiom_search_history` VALUES ('4', '18730094411', '天天', '一席之地', '1');
INSERT INTO `idiom_search_history` VALUES ('5', '18730094411', '天天', '心粗胆壮', '1');
INSERT INTO `idiom_search_history` VALUES ('6', '18730094411', '天天', '一丝不紊', '1');
INSERT INTO `idiom_search_history` VALUES ('7', '18730094411', '天天', '一丝不挂', '1');
INSERT INTO `idiom_search_history` VALUES ('8', '18730094411', '天天', '促膝长谈', '1');
INSERT INTO `idiom_search_history` VALUES ('9', '18730094411', '天天', '局促不安', '1');
INSERT INTO `idiom_search_history` VALUES ('10', '18730094415', '李仕奇', '卧冰哭竹', '1');
INSERT INTO `idiom_search_history` VALUES ('11', '18730094415', '李仕奇', '一不扭众', '1');
INSERT INTO `idiom_search_history` VALUES ('12', '18730094415', '李仕奇', '一事不知', '1');
INSERT INTO `idiom_search_history` VALUES ('13', '18831158249', '糖糖', '与世无争', '1');
INSERT INTO `idiom_search_history` VALUES ('14', '18831158249', '糖糖', '秦晋之好', '1');
INSERT INTO `idiom_search_history` VALUES ('15', '18831158249', '糖糖', '三个臭皮匠，赛过诸葛亮', '1');
INSERT INTO `idiom_search_history` VALUES ('16', '18831158249', '糖糖', '个抒几见', '1');
INSERT INTO `idiom_search_history` VALUES ('17', '18831158249', '糖糖', '姑妄言之', '1');
INSERT INTO `idiom_search_history` VALUES ('18', '18831158249', '糖糖', '姑息养奸', '1');
INSERT INTO `idiom_search_history` VALUES ('19', '18831158249', '糖糖', '丰衣足食', '1');

-- ----------------------------
-- Table structure for `moments`
-- ----------------------------
DROP TABLE IF EXISTS `moments`;
CREATE TABLE `moments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phoneNumber` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `moments_time` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `remark` char(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of moments
-- ----------------------------
INSERT INTO `moments` VALUES ('39', '18730094418', '2020-12-07 at 08:35:54 GMT', null);
INSERT INTO `moments` VALUES ('40', '18831158249', '2020-12-09 at 08:35:54 GMT', null);

-- ----------------------------
-- Table structure for `moments_attention`
-- ----------------------------
DROP TABLE IF EXISTS `moments_attention`;
CREATE TABLE `moments_attention` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personPhone` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `momentsPhone` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `whetherAttention` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of moments_attention
-- ----------------------------

-- ----------------------------
-- Table structure for `moments_comments`
-- ----------------------------
DROP TABLE IF EXISTS `moments_comments`;
CREATE TABLE `moments_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `momentsId` int(11) DEFAULT NULL,
  `comments` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `PersonPhone` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `PersonName` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `PersonHead` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `momentsClass` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of moments_comments
-- ----------------------------
INSERT INTO `moments_comments` VALUES ('15', '39', '说点什么吧', '18730094418', '小隐隐约约', 'default_avatar.png', null);
INSERT INTO `moments_comments` VALUES ('16', '39', '第一个', '18730094418', '小隐隐约约', 'default_avatar.png', null);
INSERT INTO `moments_comments` VALUES ('17', '42', '啦啦啦', '18730094418', '小隐隐约约', 'default_avatar.png', null);
INSERT INTO `moments_comments` VALUES ('18', '42', '！！\n？？', '18730094418', '小隐隐约约', 'default_avatar.png', null);
INSERT INTO `moments_comments` VALUES ('19', '39', '四角围晋级赛', '18730094411', '老大', '18730094411.png', null);

-- ----------------------------
-- Table structure for `moments_content`
-- ----------------------------
DROP TABLE IF EXISTS `moments_content`;
CREATE TABLE `moments_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `momentsContent` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `momentsId` int(11) DEFAULT NULL,
  `time` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `personalPhone` char(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of moments_content
-- ----------------------------
INSERT INTO `moments_content` VALUES ('18', '小么小二郎啊', '39', '2020-12-07 at 08:35:54 GMT', '18730094418');
INSERT INTO `moments_content` VALUES ('19', '是心动啊', '40', '2020-12-09 at 08:35:54 GMT', '18831158249');

-- ----------------------------
-- Table structure for `moments_friendname`
-- ----------------------------
DROP TABLE IF EXISTS `moments_friendname`;
CREATE TABLE `moments_friendname` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `friendName` char(255) DEFAULT NULL,
  `comentsId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of moments_friendname
-- ----------------------------

-- ----------------------------
-- Table structure for `moments_likegiveperson`
-- ----------------------------
DROP TABLE IF EXISTS `moments_likegiveperson`;
CREATE TABLE `moments_likegiveperson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `momentsId` int(11) DEFAULT NULL,
  `likegivePersonName` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `likegivePersonPhone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `likegiveboolen` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of moments_likegiveperson
-- ----------------------------
INSERT INTO `moments_likegiveperson` VALUES ('116', '40', '李哲的小迷弟', '19831127142', '1');
INSERT INTO `moments_likegiveperson` VALUES ('129', '39', '李哲的小迷弟', '19831127142', '1');
INSERT INTO `moments_likegiveperson` VALUES ('133', '42', '小隐隐约约', '18730094418', '1');
INSERT INTO `moments_likegiveperson` VALUES ('134', '40', '小隐隐约约', '18730094418', '1');
INSERT INTO `moments_likegiveperson` VALUES ('136', '39', '小隐隐约约', '18730094418', '1');

-- ----------------------------
-- Table structure for `moments_pictureurl`
-- ----------------------------
DROP TABLE IF EXISTS `moments_pictureurl`;
CREATE TABLE `moments_pictureurl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `momentsPictureUrl` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `momentsId` int(11) DEFAULT NULL,
  `time` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `personalPhone` char(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of moments_pictureurl
-- ----------------------------
INSERT INTO `moments_pictureurl` VALUES ('51', 'wangerdetonghua.jpg', '39', '2020-12-07 at 08:35:54 GMT', '18730094418');
INSERT INTO `moments_pictureurl` VALUES ('53', 'xiaoxiongweini.jpg', '39', '2020-12-07 at 08:35:54 GMT', '18730094418');
INSERT INTO `moments_pictureurl` VALUES ('54', 'yangcongtoulixianji.jpg', '39', '2020-12-07 at 08:35:54 GMT', '18730094418');
INSERT INTO `moments_pictureurl` VALUES ('55', 'yidalitonghua.jpg', '39', '2020-12-07 at 08:35:54 GMT', '18730094418');
INSERT INTO `moments_pictureurl` VALUES ('56', 'yangcongtoulixianji.jpg', '40', '2020-12-09 at 08:35:54 GMT', '18831158249');

-- ----------------------------
-- Table structure for `moments_reply`
-- ----------------------------
DROP TABLE IF EXISTS `moments_reply`;
CREATE TABLE `moments_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `momentsId` int(11) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `replyContent` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `PersonPhone` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `PersonName` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `PersonHead` char(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of moments_reply
-- ----------------------------
INSERT INTO `moments_reply` VALUES ('1', '39', '0', '说啥呢', '18730094412', '老二', '18730094412.png');
INSERT INTO `moments_reply` VALUES ('2', '39', '1', '啦啦', '18730094412', '老二', '18730094412.png');
INSERT INTO `moments_reply` VALUES ('3', '39', '0', '哈哈哈', '18730094412', '老二', '18730094412.png');

-- ----------------------------
-- Table structure for `parentmessage`
-- ----------------------------
DROP TABLE IF EXISTS `parentmessage`;
CREATE TABLE `parentmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `headportrait` varchar(50) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `sex` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parentmessage
-- ----------------------------
INSERT INTO `parentmessage` VALUES ('1', 'android1607001422406.jpg', '19831127142', 'tiantian李哲', '男');
INSERT INTO `parentmessage` VALUES ('2', 'default.jpg', '15091826027', 'lg', '女');
INSERT INTO `parentmessage` VALUES ('3', 'default_avatar.png', '18730094411', '亚雨', '女');

-- ----------------------------
-- Table structure for `parents`
-- ----------------------------
DROP TABLE IF EXISTS `parents`;
CREATE TABLE `parents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` char(11) NOT NULL,
  `password` varchar(15) NOT NULL,
  `nickname` varchar(20) NOT NULL DEFAULT 'tiantian8808',
  `avatar` varchar(20) NOT NULL DEFAULT 'default_avatar.png',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of parents
-- ----------------------------
INSERT INTO `parents` VALUES ('3', '18730094418', 'yayu152152', '小隐隐约约', 'default_avatar.png');
INSERT INTO `parents` VALUES ('4', '19831127375', 'yayu152152', '小吴', 'default_avatar.png');
INSERT INTO `parents` VALUES ('5', '18730094412', 'yayu152152', '老二', '18730094412.png');
INSERT INTO `parents` VALUES ('6', '18730094411', 'yayu152152', '老大', '18730094411.png');
INSERT INTO `parents` VALUES ('7', '18730094413', 'yayu152152', '老三', '18730094413.png');
INSERT INTO `parents` VALUES ('8', '18730094414', 'yayu152152', '老四', '18730094414.png');
INSERT INTO `parents` VALUES ('9', '18730094415', 'yayu152152', '五', '18730094415.png');
INSERT INTO `parents` VALUES ('10', '18730094416', 'yayu152152', '六儿', 'default_avatar.png');
INSERT INTO `parents` VALUES ('11', '18831158249', '123456', '小迷糊吖', 'default_avatar.png');
INSERT INTO `parents` VALUES ('12', '19831127377', 'yayu15212', '琪琪', 'default_avatar.png');
INSERT INTO `parents` VALUES ('13', '18730094400', 'yayu152152', '小鸠', 'default_avatar.png');
INSERT INTO `parents` VALUES ('14', '15230048998', 'yayu12152', '妈咪', 'default_avatar.png');
INSERT INTO `parents` VALUES ('15', '13730024418', 'yay152162', '发扣扣', 'default_avatar.png');
INSERT INTO `parents` VALUES ('16', '13730024416', 'aaaaaa', '大家都', 'default_avatar.png');
INSERT INTO `parents` VALUES ('17', '17831194418', 'kjdssks', 'kdjdj', 'default_avatar.png');
INSERT INTO `parents` VALUES ('18', '19831127142', 'li0816', 'tiantian8808', 'default_avatar.png');

-- ----------------------------
-- Table structure for `pcrelation`
-- ----------------------------
DROP TABLE IF EXISTS `pcrelation`;
CREATE TABLE `pcrelation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `childId` int(11) NOT NULL,
  `parentId` int(11) NOT NULL,
  `relationId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pcrelation
-- ----------------------------

-- ----------------------------
-- Table structure for `relation`
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of relation
-- ----------------------------

-- ----------------------------
-- Table structure for `remark`
-- ----------------------------
DROP TABLE IF EXISTS `remark`;
CREATE TABLE `remark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_phone` char(11) NOT NULL,
  `to_phone` char(11) NOT NULL,
  `remark` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of remark
-- ----------------------------
INSERT INTO `remark` VALUES ('14', '18730094411', '15230048998', '妈咪');
INSERT INTO `remark` VALUES ('15', '15230048998', '18730094411', '老大');
INSERT INTO `remark` VALUES ('18', '18730094412', '18730094411', '老大');
INSERT INTO `remark` VALUES ('19', '18730094411', '18730094412', '老二');
INSERT INTO `remark` VALUES ('20', '18730094413', '18730094411', '老大');
INSERT INTO `remark` VALUES ('21', '18730094411', '18730094413', '老三');
INSERT INTO `remark` VALUES ('22', '18730094414', '18730094411', '老大');
INSERT INTO `remark` VALUES ('23', '18730094411', '18730094414', '四儿');
INSERT INTO `remark` VALUES ('24', '18730094415', '18730094411', '老大');
INSERT INTO `remark` VALUES ('25', '18730094411', '18730094415', '五儿童');
INSERT INTO `remark` VALUES ('28', '19831127375', '18730094411', '老大');
INSERT INTO `remark` VALUES ('29', '18730094411', '19831127375', '小吴');
INSERT INTO `remark` VALUES ('61', '13730024418', '18730094411', '老大');
INSERT INTO `remark` VALUES ('62', '18730094411', '13730024418', '巴巴');
INSERT INTO `remark` VALUES ('63', '18831158249', '18730094411', '老大');
INSERT INTO `remark` VALUES ('64', '18730094411', '18831158249', '小迷糊吖');
INSERT INTO `remark` VALUES ('65', '18831158249', '18730094412', '老二');
INSERT INTO `remark` VALUES ('66', '18730094412', '18831158249', '小迷糊吖');

-- ----------------------------
-- Table structure for `types`
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES ('1', '童话');
INSERT INTO `types` VALUES ('2', '文学名著');
INSERT INTO `types` VALUES ('3', '悬疑推理');
