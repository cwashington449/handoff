import { Button } from '@/components/ui/button.tsx';
import { Dialog, DialogContent, DialogTrigger } from '@/components/ui/dialog.tsx';
import { ArrowRight, Play } from 'lucide-react';
import { Link } from 'react-router-dom';
import { ProjectScopingWizard } from './ProjectScopingWizard.tsx';
import { analytics } from '@/lib/analytics.ts';

export function HeroSection() {
  return (
    <section className="relative min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-50 via-white to-purple-50 pt-16">
      {/* Background Pattern */}
      <div className="absolute inset-0 bg-grid-pattern opacity-5"></div>
      
      {/* Floating Elements */}
      <div className="absolute top-20 left-10 w-20 h-20 bg-blue-200 rounded-full opacity-20 animate-pulse"></div>
      <div className="absolute top-40 right-20 w-16 h-16 bg-purple-200 rounded-full opacity-20 animate-pulse delay-1000"></div>
      <div className="absolute bottom-40 left-20 w-12 h-12 bg-green-200 rounded-full opacity-20 animate-pulse delay-2000"></div>
      
      <div className="relative max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
        <div className="max-w-4xl mx-auto">
          <h1 className="text-5xl md:text-7xl font-bold text-gray-900 mb-6 leading-tight">
            Your Vision,{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-blue-600 to-purple-600">
              Vibe-Coded?
            </span>
            <br />
            Get it Finished.
          </h1>
          
          <p className="text-xl md:text-2xl text-gray-600 mb-12 leading-relaxed max-w-3xl mx-auto">
            <strong>handoff</strong> is the marketplace for non-technical creators to connect with expert developers who specialize in turning stalled projects into reality.
          </p>

          <div className="flex flex-col sm:flex-row gap-4 justify-center items-center mb-16">
            <ProjectScopingWizard />
            
            <Link to="/finishers">
              <Button 
                variant="outline" 
                size="lg"
                className="border-2 border-gray-300 hover:border-gray-400 px-8 py-4 text-lg font-semibold rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105"
                onClick={() => analytics.buttonClick('become_finisher', 'hero_section')}
              >
                Become a Finisher
                <ArrowRight className="ml-2 h-5 w-5" />
              </Button>
            </Link>
          </div>

          {/* Hero Video/Animation Placeholder */}
          <div className="relative max-w-4xl mx-auto">
            <Dialog>
              <DialogTrigger asChild>
                <div className="cursor-pointer aspect-video bg-gradient-to-br from-gray-100 to-gray-200 rounded-2xl shadow-2xl overflow-hidden hover:shadow-3xl transition-shadow">
                  <div className="flex items-center justify-center h-full">
                    <div className="text-center">
                      <div className="w-20 h-20 bg-white rounded-full flex items-center justify-center shadow-lg mb-4 mx-auto hover:scale-110 transition-transform">
                        <Play className="h-8 w-8 text-blue-600 ml-1" />
                      </div>
                      <p className="text-gray-600 font-medium">Watch how handoff transforms ideas into reality</p>
                    </div>
                  </div>
                </div>
              </DialogTrigger>
              <DialogContent className="max-w-4xl">
                <div className="aspect-video bg-gray-900 rounded-lg flex items-center justify-center">
                  <div className="text-center text-white">
                    <div className="w-16 h-16 bg-white/10 rounded-full flex items-center justify-center mx-auto mb-4">
                      <Play className="h-8 w-8 text-white ml-1" />
                    </div>
                    <h3 className="text-xl font-semibold mb-2">Demo Video Coming Soon!</h3>
                    <p className="text-gray-300">
                      We're creating an amazing demo to show you exactly how handoff works. 
                      In the meantime, try our Project Scoping Wizard above!
                    </p>
                  </div>
                </div>
              </DialogContent>
            </Dialog>
            
            {/* Floating Cards */}
            <div className="absolute -top-4 -left-4 bg-white p-4 rounded-lg shadow-lg transform rotate-3 hidden lg:block">
              <div className="flex items-center space-x-2">
                <div className="w-3 h-3 bg-green-400 rounded-full"></div>
                <span className="text-sm font-medium">Project Scoped</span>
              </div>
            </div>
            
            <div className="absolute -bottom-4 -right-4 bg-white p-4 rounded-lg shadow-lg transform -rotate-3 hidden lg:block">
              <div className="flex items-center space-x-2">
                <div className="w-3 h-3 bg-blue-400 rounded-full"></div>
                <span className="text-sm font-medium">MVP Delivered</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
