# Staff Engineer (Larsen)

## Staff engineers archetype

- tech lead. Some have TLM - includes people management responsibilities
   - guides approach and execution of team
   - they are comfortable scoping complex tasks, coordinating their team towards solving them, and unblocking them along the way
   - first person to be called when roadmap needs to be shuffled
   - earlier in career, they implemented team’s most complex projects, but at this point, they delegate such projects across the team in order to **grow their team and in acknowledgement that the team’s impact grows as the tech lead’s coding blocks shrink**
   - for many folks - their first experience as Staff
   - org needs roughly 1 TL for every eight engineers - far more common than other archetypes
- architect - direction, quality, and approach within a critical area
   - resp. for success of specific technical domain within their company, for example, API design, frotnend stack, storage strategy, cloud infrastructure
   - have intimate understanding of business’s needs, their users goals, and relevant technical constraints
   - demonstrate consistently good judgment
- solver - digs deep into a complex problem and finds path forward
   - stop working on problem once it’s contained
- right hand - extends executive attention, borrowing their scope and
   - least common archetype

Which one is you? start reflecting on work that **energizes you. You’ll have long enough to spend some time sampling archetype.**

Despite archetypes, there is shared foundation:

- setting and editing tech direction
   - **far more** about understanding and solving the real needs of the org around you and **far less** about prioritizing technology and approaches that you personally are excited to learn about
- providing sponsorship and mentorship
   - you’re far more likely to change your company’s long term trajectory by growing the engineers around you than through personal heroics
- injecting engineering context into org decisions
   - providing eng. perspective
- exploration
   - hill-climbing is a simple org algorithm. Turn around - identify the highest nearby point, walk there. Once get there - repeat. **By doing this, you’ll get to the top of any mountain**
   - some companies do different approach: find a couple of trusted individuals with broad skills, allocate some resources, check back in a few months later to see what they’ve discovered. One of such engineers is often a staff eng
   - if you fail - it’s reflection on the problem, not you
- being glue
   - if you are not writing much - you’ll be reading a ton of your coworkers’ code and doing a fair number of code reviews

**It’s normal for a staff eng to end some days feeling like you haven’t accomplished anything.**

# Operating at staff

Part of the challenge is that much of the work you are doing has a much slower feedback cycle.

Main topics:

1. work on what matters
2. write an eng strategy
3. curate technical quality
4. stay aligned with authority
5. to lead, you have to follow
6. learn to never be wrong
7. create space for others
8. build a network of peers

## Work on what matters most

Finding energizing work is what has kept me at Stripe for long, pursuing impactful work.

As you get more senior, time to do your work will become increasingly scarce. Only through pacing your career to your life can you sustain yourself for the long-term.

### Avoid snacking

Snacking is doing easy and low-impact work. It gives sense of accomplishment (false). It’s ok to spend some time on snacks to keep yourself motivated between bigger accomplishments.

### Stop preening

Preening is doing low impact, but high visibility work. Many companies conflate between high-visibility and high-impact so strongly that they can’t distinguish between preening and impact.

Doing dubious work but that is frequently recognized in company meetings.

Sometimes it’s one of the ways to achieve career goals, if the company incourages this. In that case - go forth and preen.

Dig into what company values are and ensure it aligns with your intended personal growth. If all are yes man - don’t be surprised that your success will depend on those acitivities.

### Stop chasing ghosts

Ghosts of previous experience. Maintain a hold of your ego to avoid investing in meeaningless work on a grand scale.

Taking the time to understand the status quo before shifting it will always repay diligence with results.

Bad leaders: new leaders push for major changes even suspecting that efforts will fail. Org depends on such leaders more and more, and all the good results will be directed to the leader, not team. Well-being of entire company must matter to you more than being percieved as essential.

## Existential issues

First explore working on those.

**Work with room and attention**

Often folks chase what leadership says to do - but with so many people looking into it, it’s hard to find room to work on smth.

Instead, the most effective places to work are those that matter to your company but still have enough room to actually do work. What are priorities that will become critical in the near future, and by doing which ahead of time you can bring impact? Where are areas that are doing OK but could be doing great?

Sometimes you’ll find work that’s worthy of attention but leadership doesn’t value it. It can be so important that you **should advocate for it to start paying attention.** Do it as little as you can, but no less (it can fail often times).

**Foster growth**

Onboarding, mentoring and coaching are wholly neglected at many companies despite being **at least as impactful as hiring**. Spend a couple of hours per week developing the team around you, it will become your legacy long after your tech specs and PRs are forgotten.

**Edit**

Many projects are one small change away from succeeding or completion. Ability to see around corners derived from your experience, you can often shift a project’s outcomes by investing the smallest ounce of effort, and this is **some of the most valuable work you can do.**

#### What only you can

Work that won’t happen without you if you don’t do it.

TL;DR: focus on what matters, do projects that develop you, and steer towards companies that value genuine experience.

### Writing engineering strategy

To write an eng strategy - write 5 design docs and pull similarities out. That’s your engineering strategy. To write an engineering vision, write 5 engineering strategies.

Why - strategies are tool of **proactive alignment**, and also **bricks that narrow many possible futures down enough** that it’s possible to write a realistic vision**.** If you realize that you revision the same discussion over and over again - time to write a strategy.

#### Writing design docs

Write design docs for:

- any projects whose capabilities will be used by numerous future projects
- projects that meaningfully impact your users
- any work that takes more than 1 month of eng time
1. Start from a problem - the cleaner the problem statement, the more obvious is the solution
2. Keep the template simple
3. Gather and review together - write alone
4. Prefer good over perfect - better let it out quick than spend too much time to make it marginally better
5. re-read your docs after finishing implementing them

#### Synthesising design docs into strategy

Look for controversial decisions that came up in multiple designs (especially those that were hard to agree on).

Example: debating if redis could be appropriate for durable storage or only as cache. Rather than revisiting this over and over again, better reflect on those discussions and write them down as a strategy.

**Good strategies guide tradeoffs and explain  rationale behind the guidance.**

Bad strategy - only states policy without explaining why.

Examples of good strategies:

- h[ttps://multithreaded.stitchfix.com/blog/2019/08/19/framework-for-responsible-innovation/](https://multithreaded.stitchfix.com/blog/2019/08/19/framework-for-responsible-innovation/)
- h[ttps://slack.engineering/how-big-technical-changes-happen-at-slack/](https://slack.engineering/how-big-technical-changes-happen-at-slack/)

Advice for writing a good strategy:

- Start where you are - don’t wait for missing design docs, some docs are missing for a reason. Whatever you write **will change**
- Write the specifics - write until you start generalizing - then stop
- Be opinionated - if not, they won’t provide any clarity on the decision making
- Show your work - must **show your rationale behind your opinions**, especially important in the first version of the doc

Some of the best strategies at times **feel like too obvious to bother about:**

- when should we write design docs?
- which databases do we use for which use cases?
- how should we stage our migration from monolith to services?

#### Extrapolate 5 strategies into a vision

Take 5 of recent strategies, extrapolate how their tradeoffs will play out over the next two or 3 years. As you edit through contradictions and weave the thread together, you’ve written an engineering vision.

Things to focus on:

- write 2-3 years out - longer doesn’t make sense, things change too rapidly in tech; don’t write something that will expire in 6 months either
- ground in business and users - effective visions serve users and your business
- be optimistic rather than audacious (reckless); ambitious but not audacious, **should be possible but the best possible version of possible**. Write as if every project is finished on time without major setbacks. **Don’t write what will be possible with infinite resources**.
- stay concrete and specific - generalized visions are easy to agree on, but won’t help to reconcile conflicting strategies
- keep it 1-2 pages long

[Identify Controls](https://lethain.com/identify-your-controls/) you’ll use to partner with your manager

# Managing technical quality

In most cases, low technical quality isn?t a crisis; it?s the expected, normal state.

At a well-run and successful company , most of your previous technical decisions won’t meet your current quality threshold. Rather than a failure, closing the gap between your current and target technical quality is a routine , essential part of effective engineering leadership.

### The problem

As an engineering leadership team, your goal is to maintain an appropriate technical quality level while devoting as much energy as possible towards the core business.

For example, you’ll do very different work getting that critical partnership out the door for next week’s deadline **versus** building a platform that supports launching ten times faster next quarter.

Approach to managing technical quality:

1. fix the **hot spots** that are causing immediate problems
2. adopt **best practices** that are known to improve quality
3. prioritize **leverage points** that preserve quality as your software changes
4. align **technical vectors** in how your organization changes software
5. **measure technical quality** to guide deeper investment
6. spin up a **technical quality team** to create systems and tools for quality
7. run a **quality program** to measure , track and create accountability

As we dig into it further, you'll realize that technical quality is a long-term game.

### Ascending the staircase

it’s generally most effective to start with the lightest weight solutions and only progress towards massive solutions as earlier efforts collapse under the pressure of scale.

Hence, do the quick stuff first! Even if it doesn’t work, **you’ll learn more and more quickly from failing to roll out the easy stuff than failing to roll out the hard stuff**.

### Hot spots

If blame code author who didn’t correctly follow the code test process, it doesn’t reduce risk - it just makes it clear who to blame when things go wrong.

Accountability has its role, but it’s much more important to **understand the problem** at hand and **try to fix it directly than to create process-driven accountability**.

[Systems thinking](https://lethain.com/systems-thinking/) is the most transformative thinking technique I’ve encountered in my career. Still, sometimes it's better discarding a system than fixing it. Sure, you can roll out a new training program to teach your team how to write better tests, but alternatively, maybe you can just **delete the one test file where 98% of test failures happen**.

### Best practices

In theory, organizations would benefit from adopting best practices before fixing quality hot spots, but I **recommend practices after hot spotting**. Adopting best practices requires a level of organizational and leadership maturity that takes some time to develop.

Remember that a good process is [evolved rather than mandated.](https://lethain.com/good-process-is-evolved/) Study how other companies adopt similar practices, document your intended approach, experiment with the practice with a few engaged teams, sand down the rough edges, improve the documentation based on the challenges, and only then roll it out further **(!!!)**

Equally important is the idea of **limiting concurrent process rollouts.**

If you try to get teams to adopt multiple new practices simultaneously, you’re fighting for their attention with yourself. It also makes it harder to attribute impact later if you’re considering reverting or modifying one of the new practices. You ought to limit yourself to a single best practice rollout at any given time.

Genuine best practice has to be **supported by research**, and the best source of research on this topic is Accelerate (book, just bought).

The handful that I’ve found most helpful to adopt early are:

- version control
- trunk-based development
- CI / CD
- production observability (including developers on-call for the systems they write)
- working in small, atomic changes

The transition **from fixing hot spots to adopting best practices** comes when you’re overwhelmed by too many hot spots to cool. The next transition, **from best practices to leverage points**, comes when you find yourself wanting to adopt a new best practice before your in-progress best practice is working. Rather than increasing your best practice adoption-in-progress limit ([Why limiting in-progress works](https://lethain.com/limiting-wip/)), move on to the next tool.

### Leverage points

**The worst sin of performance engineering is applying effort to unproven problems.**

There are a small handful of places where extra investment preserves quality over time. I call those **quality leverage points** - the three most impactful points are **interfaces**, **stateful systems**, and **data models**.

- Interfaces: contracts between systems. Effective interfaces decouple clients from the encapsulated implementation. Delightful interfaces are [Eagerly discerning, discerningly eager](https://increment.com/apis/api-design-for-eager-discerning-developers/)
- State: hardest part of the system to change. State is the hardest part of any system to change, and that resistance to change makes stateful systems another critical leverage point. State gets complex faster than other systems and has an inertia that makes it relatively expensive to improve later. As you incorporate business obligations around security, privacy, and compliance, changing your stateful systems becomes even more challenging.
- Data models: intersection of interfaces and a state, **constraining your stateful systems capabilities down to what your application considers legal**. A good data model is:
   - rigid - only exposes what it actually supports and prevents invalid state's expression
   - tolerant of evolution over time - effective data models are not even slightly clever.

Take the extra time to approach the leverage points. If it's an interface, **integrate half a dozen clients against the mocked implementation**. If it's a data model, **represent half a dozen real scenarios**. If it's stateful, **exercise the failure modes, check the consistency behaviors, and establish performance benchmarks resembling your production scenario**.  Take everything you've learned, and pull it into a **technical specification document** that you **socialize across your team**. Gather industry feedback from peers. **Even after you begin implementation, listen to reality's voice and remain open to changes.**

### Technical vectors

Effective organizations marshal the majority of their efforts towards a **shared vision**. If you **plot every technical decision** **as a vector on a grid**, **the more those vectors point in the same direction, the more you'll accomplish over time**.

Conversely, some of the most impressive engineers I've worked with created vectors with an **extraordinary magnitude but a misaligned direction**. **Ultimately those engineers harmed their organizations in their attempts to lead it**.

Fundamental tools for aligning technical vectors:

- Give direct feedback - start with simply giving direct feedback to the individuals that are misaligned.
- Refine your your engineering strategy from tech specs, to strategy and vision
- Encapsulate your approach in your workflows and tooling. Documentation is helpful - but many will not study it. **Deliberate tools** create workflows that **nurture habits far better than training and documentation**.
- Train new team members during their onboarding. Changing folks' habits after they've formed is quite challenging, however if you get new folks aligned, then the habit-momentum will work in favor of remaining aligned.
- Use **Conway's Law**. Conway's Law argues that **organizations build software that reflects their structure**. If your organization is poorly structured, this will lead to tightly coupled or tangled software. However, it's also a force for quality if your organization's design is an effective one
- Curate technology change using [architecture reviews](https://lethain.com/scaling-consistency/), [investment strategies](https://lethain.com/magnitudes-of-exploration/), and a [structured process for adopting new tools](https://lethain.com/scaling-consistency/).

Anyway - it will take months and years to align technical vectors. There's no world where you write the vision document, and the org immediately aligns behind its brilliance. **Much more likely is that it gathers dust until you invest in building support.**

### Technical quality team

A technical quality team is a software engineering team dedicated to creating quality in your codebase.

When spinning up one of such teams, some fundamentals of success are:

1. trust metrics over intuiton
2. keep your intuiton fresh
3. listen and learn from your users
4. do fever things, but do them better. When you're building for the entire engineering organization, anything you do well will accelerate the overall organization. Anything you do poorly, including something almost great with too many rough edges, will drag everyone down
5. Don't hoard impact - don't get it

### Quality program

A quality program is an initiative led by a dedicated team to maintain technical quality across an organization.

First step is find a TPM: while you can make considerable progress on an organizational program's informational aspects without a technical program manager; however, it's a trap. You'll be crushed by the coordination overhead of solo-driving a program in a large organization.

Useful article: programs - [tips for owning the ownable](https://lethain.com/programs-owning-the-unownable/).

Core approach (**pretty much a framework for rolling out a system that touches upon many customer teams, for things like migration and etc)**:

1. identify a program sponsor
2. generate sustainable, reproducible metrics
3. identify program goals for every impacted team and a clear path for them to accomplish those goals
4. build tools and docs to support teams toward their goals
5. create a goal dashboard and share it widely
6. send **programmatic nudges** for folks behind on their goals
7. periodically review program status with your sponsor

